package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.bolis.R
import com.example.bolis.utils.Constants.Companion.IMAGE_URL
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun CatalogItem(
    name: String = "Item Name",
    location: String = "Location",
    status: String = "New",
    isFavorite: Boolean = false,
    imageUrl: String = "http://bolis.maisa.kz/storage/images/1.jpg",
    onClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .width(170.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(24.dp), clip = true)
            .clip(RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .background(White50)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AsyncImage(
            model = IMAGE_URL + imageUrl, // Replace with your image URL
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
                overflow = TextOverflow.Ellipsis,
                minLines = 2,
                maxLines = 2,
                fontFamily = fontFamily,
                color = Black50,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 6.dp)
            )
            if (isFavorite) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark_filled),
                    contentDescription = "back",
                    tint = Green50,
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .size(24.dp)
                        .clickable(onClick = onFavoriteClick)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = "back",
                    tint = Green50,
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .size(24.dp)
                        .clickable(onClick = onFavoriteClick)
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(White40)
                .border(
                    width = 1.dp,
                    color = Green50,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(vertical = 4.dp, horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (status.isEmpty()) "Unknown" else status,
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = Green50
            )
        }

        Text(
            text = location,
            fontSize = 10.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Grey30,
            modifier = Modifier.padding(horizontal = 6.dp)
        )

    }
}