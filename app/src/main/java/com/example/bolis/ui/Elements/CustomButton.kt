package com.example.bolis.ui.Elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun CustomButton(name: String = "Button", onClick: () -> Unit = {}, isCancel: Boolean = false, cornerSize: Dp = 7.dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .border(
                width = if (isCancel) 1.dp else 0.dp,
                color = if (isCancel) White40 else Color.Transparent,
                shape = RoundedCornerShape(cornerSize)
            )
            .clip(shape = RoundedCornerShape(cornerSize))
            .background(
                color = if (isCancel) Color.Transparent else Green50,
            )
            .clickable(onClick = { onClick() }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = if (isCancel) Red40 else White50,
        )
    }
}