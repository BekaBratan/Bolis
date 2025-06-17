package com.example.bolis.presentation.news

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.data.models.Author
import com.example.bolis.data.models.ItemXX
import com.example.bolis.data.models.NewsResponse
import com.example.bolis.data.models.Post
import com.example.bolis.ui.Elements.BigNewsItem
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview
@Composable
fun NewsPage(
    backButtonClicked: () -> Unit = {},
    viewModel: NewsViewModel = viewModel(),
    onItemClick: (int: Int) -> Unit = {},
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)

    var listPosts: NewsResponse by remember { mutableStateOf(NewsResponse()) }

    LaunchedEffect(Unit) {
        viewModel.getNews(sharedProvider.getToken())
    }

    viewModel.newsResponse.observeForever { response ->
        if (response != null) {
            listPosts = response
            Log.d("News", response.toString())
        } else {
            Log.d("News", "Response is null")
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                rememberScrollState(),
                orientation = Orientation.Vertical,
                enabled = true
            )
            .padding(horizontal = 24.dp, vertical = 28.dp),
    ) {
        item {
            Spacer(Modifier.size(48.dp))
        }

        items(listPosts.posts?.size ?: 0) { index ->
            BigNewsItem(
                listPosts.posts?.get(index) ?: Post(),
                onItemClick = { onItemClick(it) }
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Grey30)
            )
        }
    }

    Box(
        modifier = Modifier
            .padding(top = 28.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth(),
    ) {
        CustomBackButton(
            name = "Back"
        ) { backButtonClicked() }
        Text(
            text = "News",
            fontSize = 17.sp,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Black50,
            modifier = Modifier.fillMaxWidth()
        )
    }
}