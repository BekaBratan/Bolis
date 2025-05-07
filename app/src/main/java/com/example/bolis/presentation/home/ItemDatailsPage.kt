package com.example.bolis.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.bolis.R
import com.example.bolis.data.models.Item
import com.example.bolis.data.models.ItemX
import com.example.bolis.data.models.LikedItem
import com.example.bolis.data.models.SuggestionsResponse
import com.example.bolis.presentation.onboarding.OnboardingPage1
import com.example.bolis.presentation.onboarding.OnboardingPage2
import com.example.bolis.presentation.onboarding.OnboardingPage3
import com.example.bolis.ui.Elements.CatalogItem
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Dark10
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey20
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Grey40
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.Constants.Companion.IMAGE_URL
import com.example.bolis.utils.SharedProvider
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@Preview
@Composable
fun ItemDetailsPage(
    viewModel: HomeViewModel = viewModel(),
    backButtonClicked: () -> Unit = {},
    onZoomClick: (images: List<String>) -> Unit = {},
    onItemClick: (id: Int) -> Unit = {},
    itemID: Int = 0,
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)
    var images by remember { mutableStateOf<List<String>>(listOf(
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300",
        "https://picsum.photos/200/300"
    ))}

    var likedItems: List<LikedItem?>? = emptyList()

    var suggestionsBody by remember { mutableStateOf(SuggestionsResponse(
        suggestions = List(10) { Item() }
    )) }

    var item: ItemX? by remember { mutableStateOf(null) }
    var isFavorite by remember { mutableStateOf(false) }

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })

    LaunchedEffect(Unit) {
        viewModel.getItemDetails(sharedProvider.getToken(), itemID)
        viewModel.getLikedItems(sharedProvider.getToken())
        viewModel.getSuggestions(sharedProvider.getToken())
    }

    viewModel.likedItemsResponse.observeForever {
        likedItems = it?.likedItems
    }

    viewModel.itemDetailsResponse.observeForever { response ->
        images = response?.images?.map { it.imagePath.toString() } ?: emptyList()
        isFavorite = likedItems?.any { it?.itemId == itemID } == true
        item = response?.item
    }

    viewModel.suggestionsResponse.observeForever { response ->
        if (response != null) {
            suggestionsBody = response
            Log.d("Catalog", response.toString())
        } else {
            Log.d("Catalog", "Response is null")
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
                    HorizontalPager(
                        state = pagerState
                    ) { page ->
                        ImageCard(imageUrl = images[page])
                    }

                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier.padding(12.dp),
                    )

                    Spacer(Modifier.size(10.dp))
                }

                Row(
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Zoom",
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        fontFamily = fontFamily,
                        color = Grey30,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_increase),
                        contentDescription = "zoom",
                        tint = Grey30,
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .size(24.dp)
                            .clickable(onClick = { onZoomClick(images) })
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
                        text = item?.name ?: "Item name",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        fontFamily = fontFamily,
                        color = Black40,
                    )

                    Row (
                        Modifier.padding(top = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row (
                            Modifier
                                .border(1.dp, Green50, RoundedCornerShape(10.dp))
                                .padding(horizontal = 5.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "Status: ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Black40,
                            )
                            Text(
                                text = if (item?.condition.isNullOrEmpty()) "Unknown" else item?.condition?.uppercase() ?: "Unknown",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Green50,
                            )
                        }

                        Row (
                            Modifier
                                .padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = "Item code: ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Grey30,
                            )
                            Text(
                                text = if (item?.iD!=null) item?.iD.toString() else "0000",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Black40,
                            )
                        }
                    }
                }

                if (isFavorite) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark_filled),
                        contentDescription = "favorite",
                        tint = Green50,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = {
                                isFavorite = false
                            })
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = "favorite",
                        tint = Green50,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = {
                                isFavorite = true
                            })
                    )
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
                    text = "Description",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = fontFamily,
                    color = Black40,
                )

                Text(
                    text = item?.description ?: "Item description",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = fontFamily,
                    color = Grey30,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            Spacer(Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Grey20))

            Text(
                text = "Similar Items",
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Black50,
                modifier = Modifier.padding(top = 12.dp, start = 24.dp, end = 24.dp)
            )
        }

        items(suggestionsBody.suggestions.chunked(2)) { pair ->
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
                            imageUrl = item.images?.firstOrNull()?.imagePath.orEmpty(),
                            onClick = { onItemClick(item.iD) },
                            onFavoriteClick = { /* Handle favorite click */ }
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun ImageCard(
    imageUrl: String = "https://picsum.photos/200/300"
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = IMAGE_URL + imageUrl, // Replace with your image URL
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(275.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Grey20)
        )
    }
}

@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(pagerState.pageCount) {
            val color = if (pagerState.currentPage > it) Green50 else Grey30
            Row(
                modifier = Modifier
                    .size(8.dp)
                    .background(color, shape = CircleShape)
            ) {
                if (pagerState.currentPage == it) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Green50, shape = CircleShape)
                    )
                }
            }
        }
    }
}