package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.Image
import coil3.compose.AsyncImage
import com.example.bolis.R
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.Blur
import com.example.bolis.ui.theme.Blur10
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey20
import com.example.bolis.ui.theme.fontFamily
import com.yandex.mapkit.geometry.Circle

@Preview
@Composable
fun ProfileImageCard(onClick: () -> Unit = {}, url: String = "") {
    Box(
        modifier = Modifier
            .clickable(onClick = { onClick() })
            .clip(CircleShape)
            .size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = url, // Replace with your image URL
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Green50,
                    shape = CircleShape
                )
        )
//        Image(
//            painter = painterResource(R.drawable.ic_profile_img),
//            contentScale = ContentScale.Fit,
//            contentDescription = "show",
//            modifier = Modifier
//                .size(50.dp)
//                .clickable(onClick = { })
//        )
        Text(
            text = "Change",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Blue50,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Blur10)
                .padding(top = 5.dp, bottom = 10.dp)
                .fillMaxWidth()
        )
    }
}