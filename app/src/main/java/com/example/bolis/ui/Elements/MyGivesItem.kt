package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.data.models.Product
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun MyGivesItem(
    product: Product = Product(name = "Iphone ProMaxMaxMaxPlus"),
    onEditButtonClick: () -> Unit = {},
    onDeleteButtonClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Column (
            modifier = Modifier
                .width(170.dp)
                .shadow(12.dp, shape = RoundedCornerShape(24.dp), spotColor = Black50)
                .clip(shape = RoundedCornerShape(24.dp))
                .background(White50)
                .padding(top = 12.dp, start = 20.dp, end = 20.dp, bottom = 5.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(125.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
            )
            Spacer(Modifier.size(6.dp))
            Text(
                text = product.name,
                fontSize = 14.sp,
                maxLines = 1,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                overflow = TextOverflow.Ellipsis,
                softWrap = false,
                color = Black40,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(Modifier.size(16.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomButton(
                name = "Edit",
                onClick = {
                    onEditButtonClick()
                },
                cornerSize = 12.dp
            )
            Spacer(Modifier.size(14.dp))
            CustomButton(
                name = "Delete",
                onClick = {
                    onDeleteButtonClick()
                },
                isCancel = true,
                cornerSize = 12.dp
            )
        }
    }
}