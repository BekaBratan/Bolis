package com.example.bolis.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.R
import com.example.bolis.data.api.navBarStateChange
import com.example.bolis.data.models.CatalogResponse
import com.example.bolis.data.models.Item
import com.example.bolis.data.models.LikedItem
import com.example.bolis.data.models.LikedItemsListResponse
import com.example.bolis.ui.Elements.CatalogItem
import com.example.bolis.ui.Elements.SearchBar
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview
@Composable
fun SearchPage(
    viewModel: HomeViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onItemClick: (id: Int) -> Unit = {},
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)
    navBarStateChange(false)

    var searchBody by remember { mutableStateOf(CatalogResponse(
        items = List(0) { Item() },
        searchQuery = "",
        suggestions = ""
    )) }

    var likedItemsList by remember { mutableStateOf(LikedItemsListResponse(
        likedItems = listOf()
    )) }

    var searchText by remember { mutableStateOf("") }
    var isSearched by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getCatalog(sharedProvider.getToken())
        viewModel.getLikedItems(sharedProvider.getToken())
        Log.d("Catalog", viewModel.catalogResponse.value.toString())
        Log.d("Suggestions", viewModel.suggestionsResponse.value.toString())
    }

    viewModel.likedItemsResponse.observeForever { response ->
        if (response != null) {
            likedItemsList = response
            Log.d("Catalog", response.toString())
        } else {
            Log.d("Catalog", "Response is null")
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box (modifier = Modifier.padding(20.dp)) {
            SearchBar(
                searchText = searchText,
                setSearchText = { searchText = it },
                isFavorite = false,
                isChat = false,
                isSearch = true,
                onBackClick = onBackClick,
                onSearch = {
                    isSearched = true
//                    viewModel.searchItem(searchText) { response ->
//                        searchBody = response
//                    }
                },
            )
        }

        if (searchBody.items.isNotEmpty()) {
            Text(
                text = "We found ${searchBody.items.size} results",
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                color = Black50,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (20.dp))
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(1800.dp)
            ) {
                item {
                    Spacer(Modifier.size(4.dp))
                }
                item {
                    Spacer(Modifier.size(4.dp))
                }
                items(searchBody.items) { item ->
                    CatalogItem(
                        name = item.name,
                        status = item.condition,
                        isFavorite = likedItemsList.likedItems?.any { it?.itemId == item.iD } == true,
                        onClick = { onItemClick(item.iD) },
                        onFavoriteClick = {
                            if (likedItemsList.likedItems?.any { it?.itemId == item.iD } == true) {
                                likedItemsList = likedItemsList.copy(
                                    likedItems = likedItemsList.likedItems?.filter { it?.itemId != item.iD }
                                )
                            } else {
                                likedItemsList = likedItemsList.copy(
                                    likedItems = likedItemsList.likedItems?.plus(LikedItem(item.iD))
                                )
                            }
                            viewModel.likeItem(sharedProvider.getToken(), item.iD)
                        }
                    )
                }
                item {
                    Spacer(Modifier.size(4.dp))
                }
                item {
                    Spacer(Modifier.size(4.dp))
                }
            }
        } else if (isSearched) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "There is no results",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = fontFamily,
                    color = Red40,
                    modifier = Modifier
                        .padding(horizontal = (20.dp))
                )
            }
        } else {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_box_search),
                    contentDescription = "Search",
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = "What are we going to look for?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = fontFamily,
                    color = Green50,
                    modifier = Modifier
                        .padding(horizontal = (20.dp))
                )
            }
        }
    }
}