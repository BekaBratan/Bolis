package com.example.bolis.presentation.onboarding

import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Dark10
import com.example.bolis.ui.theme.Dark20
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.Green20
import com.example.bolis.ui.theme.Green40
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey20
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun OnboardingScreen(onGetStartedClick: () -> Unit = {}) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green40),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(16.dp),
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> OnboardingPage1()
                1 -> OnboardingPage2()
                2 -> OnboardingPage3()
                else -> onGetStartedClick()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(White50)
        ) {
            if (pagerState.currentPage < 2) {
                Row(
                    modifier = Modifier
                        .padding(25.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Green40)
                        .fillMaxWidth()
                        .height(54.dp)
                        .padding(vertical = 16.dp, horizontal = 25.dp)
                        .clickable(onClick = {
                            coroutineScope
                                .launch { // Use coroutineScope for animations
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                        }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Next",
                        fontFamily = fontFamily,
                        color = White50,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500)
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_next),
                        contentScale = ContentScale.Fit,
                        contentDescription = "next",
                        modifier = Modifier
                            .size(24.dp),
                        colorFilter = ColorFilter.tint(
                            color = White50
                        )
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .padding(25.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Green40)
                        .fillMaxWidth()
                        .height(54.dp)
                        .padding(vertical = 16.dp, horizontal = 25.dp)
                        .clickable(onClick = onGetStartedClick),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Get Start",
                        fontFamily = fontFamily,
                        color = White50,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500)
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_next),
                        contentScale = ContentScale.Fit,
                        contentDescription = "get start",
                        modifier = Modifier
                            .size(24.dp),
                        colorFilter = ColorFilter.tint(
                            color = White50
                        )
                    )
                }
            }
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
     Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
         repeat(pagerState.pageCount) {
             val color = if (pagerState.currentPage > it) White50 else Dark10
             Row(
                 modifier = Modifier
                     .height(8.dp)
                     .weight(1f)
                     .background(color, shape = CircleShape)
             ) {
                 if (pagerState.currentPage == it) {
                     val animatedWidth = remember { androidx.compose.animation.core.Animatable(0f) }
                     LaunchedEffect(pagerState.currentPage) {
                         animatedWidth.snapTo(0f)
                         animatedWidth.animateTo(
                             targetValue = 1f,
                             animationSpec = androidx.compose.animation.core.tween(durationMillis = 3500, easing = LinearEasing)
                         )
                         coroutineScope.launch {
                             pagerState.animateScrollToPage(pagerState.currentPage + 1)
                         }
                     }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(animatedWidth.value)
                            .fillMaxHeight()
                            .background(White50, shape = CircleShape)
                    )
                 }
             }
         }
     }
}