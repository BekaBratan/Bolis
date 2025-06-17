package com.example.bolis.presentation.news

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.bolis.R
import com.example.bolis.data.models.Author
import com.example.bolis.data.models.ItemX
import com.example.bolis.data.models.LikedItem
import com.example.bolis.data.models.NewsResponse
import com.example.bolis.data.models.Post
import com.example.bolis.presentation.home.HomeViewModel
import com.example.bolis.presentation.home.HorizontalPagerIndicator
import com.example.bolis.presentation.home.ImageCard
import com.example.bolis.ui.Elements.CatalogItem
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey20
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.Constants.Companion.IMAGE_URL
import com.example.bolis.utils.SharedProvider
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import kotlin.collections.chunked

@Preview
@Composable
fun NewsDetailsPage(
    newsID: Int = 0,
    viewModel: NewsViewModel = viewModel(),
    backButtonClicked: () -> Unit = {},
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)

    var post: Post? by remember { mutableStateOf(Post(
        id = 0,
        title = "Sample Post Title",
        content = "This is a sample content for the post.",
        createdAt = "2025-05-08T02:55:55.476084+05:00",
        author = Author(
            firstName = "John",
            lastName = "Doe",
            avatar = "https://example.com/avatar.jpg"
        ),
        userId = 0
    )) }

    var listPosts: NewsResponse by remember { mutableStateOf(NewsResponse()) }

    LaunchedEffect(Unit) {
        viewModel.getNews(sharedProvider.getToken())
    }

    viewModel.newsResponse.observeForever { response ->
        if (response != null) {
            listPosts = response
            Log.d("News", response.toString())
            post = response.posts?.find { it?.id == newsID } ?: Post(
                id = 0,
                title = "Post not found",
                content = "The requested post does not exist.",
                createdAt = "2025-05-08T02:55:55.476084+05:00",
                author = Author(
                    firstName = "Unknown",
                    lastName = "Author",
                    avatar = ""
                ),
                userId = 0
            )
        } else {
            Log.d("News", "Response is null")
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White50),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            CustomBackButton(
                modifier = Modifier
                    .padding(top = 28.dp, start = 24.dp),
                name = stringResource(R.string.back)
            ) { backButtonClicked() }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = IMAGE_URL + post?.author?.avatar, // Replace with your image URL
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }

            Spacer(Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Grey20))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .weight(1f),
                ) {
                    Text(
                        text = post?.title ?: "Post title",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        fontFamily = fontFamily,
                        color = Black40,
                    )

                    Column (
                        Modifier.padding(top = 6.dp),
                    ) {
                        Row {
                            Text(
                                text = "Author: ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Black40,
                            )
                            Text(
                                text = "${post?.author?.lastName} ${post?.author?.firstName}" ?: "Author name",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Green50,
                            )
                        }

                        Row (
                            Modifier
                                .padding(top = 10.dp)
                        ) {
                            Text(
                                text = "Date: ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Black40,
                            )
                            Text(
                                text = "${post?.createdAt ?: "Date"}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Grey30,
                            )
                        }
                    }
                }
            }

            Spacer(Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Grey20))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
            ) {
                Text(
                    text = "Content",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = fontFamily,
                    color = Black40,
                )

                Text(
                    text = post?.content ?: "Post content goes here",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = fontFamily,
                    color = Grey30,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }
    }
}