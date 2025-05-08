package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.bolis.data.models.ItemXX
import com.example.bolis.data.models.Product
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.Constants.Companion.IMAGE_URL
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

@Preview
@Composable
fun MyGivesItem(
    product: ItemXX = ItemXX(name = "Iphone ProMaxMaxMaxPlus", createdAt = "2025-05-08T02:55:55.476084+05:00"),
    onEditButtonClick: () -> Unit = {},
    onDeleteButtonClick: () -> Unit = {},
    onReasonButtonClick: () -> Unit = {},
    onItemClick: (int: Int) -> Unit = {},
) {
    val dateTime = OffsetDateTime.parse(product.createdAt ?: "")
    val date = dateTime.toLocalDate()
    val time = dateTime.toLocalTime().truncatedTo(ChronoUnit.SECONDS)

    Row(
        modifier = Modifier.clickable(onClick = {
            onItemClick(product.iD ?: 0)
        }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            modifier = Modifier
                .width(170.dp)
                .shadow(12.dp, shape = RoundedCornerShape(24.dp), spotColor = Black50)
                .clip(shape = RoundedCornerShape(24.dp))
                .background(White50)
                .padding(top = 12.dp, start = 20.dp, end = 20.dp, bottom = 5.dp)
        ) {
            AsyncImage(
                model = IMAGE_URL + product.images?.firstOrNull()?.imagePath, // Replace with your image URL
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(Modifier.size(6.dp))
        }
        Spacer(Modifier.size(16.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = product.name ?: "",
                fontSize = 16.sp,
                maxLines = 2,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                overflow = TextOverflow.Ellipsis,
                color = Black40,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.size(14.dp))
            Text(
                text = date.toString(),
                fontSize = 16.sp,
                maxLines = 2,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                overflow = TextOverflow.Ellipsis,
                color = Black40,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = time.toString(),
                fontSize = 16.sp,
                maxLines = 2,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                overflow = TextOverflow.Ellipsis,
                color = Black40,
                modifier = Modifier.fillMaxWidth()
            )
//            if (product.status == "") {
//                CustomButton(
//                    name = "Edit",
//                    onClick = {
//                        onEditButtonClick()
//                    },
//                    cornerSize = 12.dp
//                )
//                Spacer(Modifier.size(14.dp))
//                CustomButton(
//                    name = "Delete",
//                    onClick = {
//                        onDeleteButtonClick()
//                    },
//                    cornerSize = 12.dp,
//                    strokeColor = Black40,
//                    backgroundColor = Color.Transparent,
//                    textColor = Red40
//                )
//            } else if (product.status == 1) {
//                CustomButton(
//                    name = "Reason for cancellation",
//                    onClick = {
//                        onReasonButtonClick()
//                    },
//                    cornerSize = 12.dp,
//                    strokeColor = Red40,
//                    backgroundColor = Color.Transparent,
//                    textColor = Red40
//                )
//            } else {
//                CustomButton(
//                    name = "Reason for cancellation",
//                    onClick = {
//                        onReasonButtonClick()
//                    },
//                    cornerSize = 12.dp,
//                    strokeColor = Red40,
//                    backgroundColor = Color.Transparent,
//                    textColor = Red40
//                )
//            }
        }
    }
}