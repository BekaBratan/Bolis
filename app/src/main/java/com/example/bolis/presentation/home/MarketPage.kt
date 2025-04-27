package com.example.bolis.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.data.models.CatalogResponse
import com.example.bolis.data.models.Item
import com.example.bolis.ui.Elements.CatalogItem
import com.example.bolis.ui.Elements.WebView
import com.example.bolis.utils.SharedProvider

@Preview(showBackground = true)
@Composable
fun MarketPage(
    viewModel: HomeViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)

    var catalogBody by remember { mutableStateOf(CatalogResponse(
        items = List(10) { Item() },
        searchQuery = "",
        suggestions = ""
    )) }

    LaunchedEffect(Unit) {
        viewModel.getCatalog(sharedProvider.getToken())
        Log.d("Catalog", viewModel.catalogResponse.value.toString())
    }

    viewModel.catalogResponse.observeForever { response ->
        if (response != null) {
            catalogBody = response
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

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp),
    ) {
        item {
            Spacer(Modifier.size(20.dp))
        }
        item {
            Spacer(Modifier.size(20.dp))
        }
        items(catalogBody.items) { item ->
            CatalogItem(
                name = item.name,
                status = item.condition,
                imageUrl = item.images[0].imagePath,
            )
        }
        item {
            Spacer(Modifier.size(20.dp))
        }
        item {
            Spacer(Modifier.size(20.dp))
        }
    }
}