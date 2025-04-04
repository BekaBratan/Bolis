package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun UploadImageButton(isError: Boolean = false, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = "Upload image of product*",
            fontSize = 15.sp,
            fontWeight = FontWeight(600),
            fontFamily = fontFamily,
            color = Black40
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(White40)
                .border(
                    width = if (isError) 1.dp else 0.dp,
                    color = if (isError) Red50 else Color.Transparent,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 18.dp),
                text = "JPEG,PNG weighing no more than 10MB",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Grey30
            )
            val image = R.drawable.ic_image
            Image(
                painter = painterResource(image),
                contentScale = ContentScale.Fit,
                contentDescription = "image",
                modifier = Modifier
                    .size(50.dp),
                colorFilter = ColorFilter.tint(Grey30)
            )
            CustomButton(name = "Upload image", onClick = { onClick() })
        }
    }
}