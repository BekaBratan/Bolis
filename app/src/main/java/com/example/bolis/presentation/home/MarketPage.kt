package com.example.bolis.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.example.bolis.data.models.Suggestion
import com.example.bolis.data.models.SuggestionsResponse
import com.example.bolis.ui.Elements.CatalogItem
import com.example.bolis.ui.Elements.WebView
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Green10
import com.example.bolis.ui.theme.Green20
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview(showBackground = true)
@Composable
fun MarketPage(
    viewModel: HomeViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)
    navBarStateChange(true)

    var catalogBody by remember { mutableStateOf(CatalogResponse(
        items = List(10) { Item() },
        searchQuery = "",
        suggestions = ""
    )) }

    var suggestionsBody by remember { mutableStateOf(SuggestionsResponse(
        suggestions = List(10) { Suggestion() }
    )) }

    LaunchedEffect(Unit) {
        viewModel.getCatalog(sharedProvider.getToken())
        viewModel.getSuggestions(sharedProvider.getToken())
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

    viewModel.errorMessageResponse.observeForever { error ->
        if (error != null) {
            Log.d("Catalog", "Error: ${error.error}")
        }
    }

    LazyColumn {

        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
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
        }

        item {
            Text(
                text = "Recommendations",
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
                        imageUrl = item.imagePath,
                    )

                }
                item {
                    Spacer(Modifier.size(4.dp))
                }
            }
        }

        item {
            Text(
                text = "Donated Items",
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontFamily = fontFamily,
                color = Black50,
                modifier = Modifier
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 0.dp)
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
                items(catalogBody.items) { item ->
                    CatalogItem(
                        name = item.name,
                        status = item.condition,
                        imageUrl = item.images[0].imagePath,
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
}