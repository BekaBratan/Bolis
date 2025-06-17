package com.example.bolis.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
import com.example.bolis.data.models.NewsResponse
import com.example.bolis.data.models.Post
import com.example.bolis.data.models.SuggestionsResponse
import com.example.bolis.ui.Elements.CatalogItem
import com.example.bolis.ui.Elements.NewsItem
import com.example.bolis.ui.Elements.SearchBar
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Green20
import com.example.bolis.ui.theme.Green40
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview(showBackground = true)
@Composable
fun MarketPage(
    viewModel: HomeViewModel = viewModel(),
    onSearchClick: () -> Unit = {},
    onChatClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onNewsClick: () -> Unit = {},
    onNewsItemClick: (id: Int) -> Unit = {},
    onCatalogClick: (catalogName: String, catalogId: Int) -> Unit = {catalogName, catalogId ->},
    onItemClick: (id: Int) -> Unit = {},
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)
    navBarStateChange(true)

    var catalogBody by remember { mutableStateOf(CatalogResponse(
        items = List(10) { Item() },
        searchQuery = "",
        suggestions = ""
    )) }

    var listPosts: NewsResponse by remember { mutableStateOf(NewsResponse()) }

    var suggestionsBody by remember { mutableStateOf(SuggestionsResponse(
        suggestions = List(10) { Item() }
    )) }

    var likedItemsList by remember { mutableStateOf(LikedItemsListResponse(
        likedItems = listOf()
    )) }

    LaunchedEffect(Unit) {
        viewModel.getCatalog(sharedProvider.getToken())
        viewModel.getSuggestions(sharedProvider.getToken())
        viewModel.getNews(sharedProvider.getToken())
        viewModel.getLikedItems(sharedProvider.getToken())
        Log.d("Catalog", viewModel.catalogResponse.value.toString())
        Log.d("Suggestions", viewModel.suggestionsResponse.value.toString())
    }

    viewModel.catalogResponse.observeForever { response ->
        if (response != null) {
            catalogBody = response
            Log.d("Catalog", response.toString())
        } else {
            Log.d("Catalog", "Response is null")
        }
    }

    viewModel.suggestionsResponse.observeForever { response ->
        if (response != null) {
            suggestionsBody = response
            Log.d("Catalog", response.toString())
        } else {
            Log.d("Catalog", "Response is null")
        }
    }

    viewModel.newsResponse.observeForever { response ->
        if (response != null) {
            listPosts = response
            Log.d("News", response.toString())
        } else {
            Log.d("News", "Response is null")
        }
    }

    viewModel.likedItemsResponse.observeForever { response ->
        if (response != null) {
            likedItemsList = response
            Log.d("Catalog", response.toString())
        } else {
            Log.d("Catalog", "Response is null")
        }
    }

    viewModel.errorMessageResponse.observeForever { error ->
        if (error != null) {
            Log.d("Catalog", "Error: ${error.error}")
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Box (modifier = Modifier.padding(20.dp)) {
                SearchBar(
                    onSearchClick = onSearchClick,
                    onFavoriteClick = onFavoriteClick,
                    onChatClick = onChatClick
                )
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Green50)
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_profile_image),
                    contentScale = ContentScale.Crop,
                    contentDescription = "chat",
                )

                Column (
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Welcome to BOLIS!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontFamily = fontFamily,
                        color = White50
                    )

                    Text(
                        text = "Hello! We're here to connect people who want to give and those in need. Join our community!",
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = fontFamily,
                        color = Green20
                    )
                }
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "News",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontFamily = fontFamily,
                    color = Black50,
                )
                Text(
                    text = "more",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontFamily = fontFamily,
                    color = Green40,
                    modifier = Modifier
                        .clickable(onClick = onNewsClick)
                )
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
            ) {
                item {
                    Spacer(Modifier.size(4.dp))
                }
                items(listPosts.posts?: List(10) { Post() }) { item ->

                    NewsItem(
                        name = item?.title.toString(),
                        imageUrl = item?.author?.avatar.toString(),
                        onClick = { onNewsItemClick(item?.id ?: 0) },
                    )

                }
                item {
                    Spacer(Modifier.size(4.dp))
                }
            }
//
//            LazyRow(
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .padding(vertical = 20.dp)
//                    .fillMaxWidth()
//            ) {
//                item {
//                    Spacer(Modifier.size(4.dp))
//                }
//                items(suggestionsBody.suggestions) { item ->
//
//                    CatalogItem(
//                        name = item.name,
//                        status = item.condition,
//                        isFavorite = likedItemsList.likedItems?.any { it?.itemId == item.iD } == true,
//                        imageUrl = item.images?.firstOrNull()?.imagePath.orEmpty(),
//                        onClick = { onItemClick(item.iD) },
//                        onFavoriteClick = {
//                            if (likedItemsList.likedItems?.any { it?.itemId == item.iD } == true) {
//                                likedItemsList = likedItemsList.copy(
//                                    likedItems = likedItemsList.likedItems?.filter { it?.itemId != item.iD }
//                                )
//                            } else {
//                                likedItemsList = likedItemsList.copy(
//                                    likedItems = likedItemsList.likedItems?.plus(LikedItem(item.iD))
//                                )
//                            }
//                            viewModel.likeItem(sharedProvider.getToken(), item.iD)
//                        }
//                    )
//
//                }
//                item {
//                    Spacer(Modifier.size(4.dp))
//                }
//            }

            Text(
                text = "Recomendations",
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontFamily = fontFamily,
                color = Black50,
                modifier = Modifier
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 0.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
            ) {
                item {
                    Spacer(Modifier.size(4.dp))
                }
                items(suggestionsBody.suggestions) { item ->

                    CatalogItem(
                        name = item.name,
                        status = item.condition,
                        isFavorite = likedItemsList.likedItems?.any { it?.itemId == item.iD } == true,
                        imageUrl = item.images?.firstOrNull()?.imagePath.orEmpty(),
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
            }

            Text(
                text = "Donated Items",
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontFamily = fontFamily,
                color = Black50,
                modifier = Modifier
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            )
        }

        items(catalogBody.items.chunked(2)) { pair ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    pair.forEach { item ->
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
                }
            }
        }

        item {
            Spacer(Modifier.size(4.dp))
        }
    }
}