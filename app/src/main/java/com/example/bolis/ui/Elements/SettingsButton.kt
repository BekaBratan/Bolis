package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import com.example.bolis.R
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey10
import com.example.bolis.ui.theme.Grey50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun SettingsButton(name: String = "Button", onClick: () -> Unit = {}, icon: Int = R.drawable.ic_lock) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .border(2.dp, Green50, RoundedCornerShape(15.dp))
            .clip(shape = RoundedCornerShape(15.dp))
            .background(Grey10)
            .clickable(onClick = { onClick() })
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(icon),
            contentScale = ContentScale.Fit,
            contentDescription = "show",
            modifier = Modifier
                .size(20.dp)
                .clickable(onClick = {
                })
        )
        Box(
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = Grey50
            )
        }
        Image(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentScale = ContentScale.Fit,
            contentDescription = "show",
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .clickable(onClick = {
                })
        )
    }
}