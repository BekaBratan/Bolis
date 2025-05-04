package com.example.bolis.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.bolis.ui.Elements.CatalogItem
import com.example.bolis.ui.Elements.SearchBar
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview
@Composable
fun SearchPage(
    viewModel: HomeViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)
    navBarStateChange(false)

    var searchBody by remember { mutableStateOf(CatalogResponse(
        items = List(10) { Item() },
        searchQuery = "",
        suggestions = ""
    )) }

    var searchText by remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 28.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "Search",
                tint = Green50
            )
            SearchBar(
                searchText = searchText,
                setSearchText = { searchText = it },
            )

        }

        Text(
            text = "We found ${searchBody.items.size} results",
            fontSize = 22.sp,
            fontWeight = FontWeight(600),
            fontFamily = fontFamily,
            color = Black50,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
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
                    imageUrl = item.images.firstOrNull()?.imagePath.orEmpty(),
                )
            }
            item {
                Spacer(Modifier.size(4.dp))
            }
            item {
                Spacer(Modifier.size(4.dp))
            }
        }
    }
}