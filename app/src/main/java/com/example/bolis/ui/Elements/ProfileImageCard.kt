package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun ProfileImageCard(onClick: () -> Unit = {}, url: String = "") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .border(2.dp, Green50, RoundedCornerShape(5.dp))
            .clip(shape = RoundedCornerShape(5.dp))
            .clickable(onClick = { onClick() })
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = url, // Replace with your image URL
            contentDescription = "Profile Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(5.dp))
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
            text = "Change Profile Image",
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Blue50
        )
    }
}