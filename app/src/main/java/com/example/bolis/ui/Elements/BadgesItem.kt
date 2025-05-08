package com.example.bolis.ui.Elements

import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.example.bolis.ui.theme.Dark10
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Grey40
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily
import kotlinx.coroutines.launch

@Preview
@Composable
fun BadgesItem(
    name: String = "Item Name",
    description: String = "Location",
    status: Int = 85,
    imageUrl: String = "http://bolis.maisa.kz/storage/images/1.jpg",
    onClick: () -> Unit = {},
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
        Image(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "back",
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
                .clip(RoundedCornerShape(12.dp))
        )
//        AsyncImage(
//            model = IMAGE_URL + imageUrl, // Replace with your image URL
//            contentDescription = "Profile Image",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(125.dp)
//                .clip(RoundedCornerShape(12.dp))
//        )

        Text(
            text = name,
            fontSize = 15.sp,
            fontWeight = FontWeight(600),
            overflow = TextOverflow.Ellipsis,
            minLines = 1,
            maxLines = 1,
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Black50,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = description,
            fontSize = 10.sp,
            fontWeight = FontWeight(500),
            overflow = TextOverflow.Ellipsis,
            minLines = 2,
            maxLines = 2,
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Grey40,
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .border(1.dp, Grey30, shape = RoundedCornerShape(4.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(status/100f)
                    .fillMaxHeight()
                    .background(Green50, shape = CircleShape)
            )
        }
    }
}