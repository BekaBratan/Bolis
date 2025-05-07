package com.example.bolis.presentation.home

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.bolis.R
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey20
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.Constants.Companion.IMAGE_URL

@Preview
@Composable
fun ZoomPage(
    backButtonClicked: () -> Unit = {},
    images: List<String> = listOf(
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300"
    )
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 100.dp, horizontal = 32.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState
            ) { page ->
                ZoomImageCard(imageUrl = images[page])
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.padding(12.dp),
            )

            Spacer(Modifier.size(10.dp))
        }

        Row(
            Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cross),
                contentDescription = "cancel",
                tint = Green50,
                modifier = Modifier
                    .size(24.dp)
                    .border(2.dp, Green50, CircleShape)
                    .clickable(onClick = backButtonClicked)
            )
        }
    }
}

@Composable
fun ZoomImageCard(
    imageUrl: String = "https://picsum.photos/200/300"
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = IMAGE_URL + imageUrl, // Replace with your image URL
            contentDescription = "Profile Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(Grey20)
        )
    }
}