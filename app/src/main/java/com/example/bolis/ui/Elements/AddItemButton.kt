package com.example.bolis.ui.Elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun AddItemButton(name: String = "Button", isSelected: Boolean = false, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .width(84.dp)
            .height(42.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(White40)
            .border(
                width = if (isSelected) 1.dp else 0.dp,
                color = if (isSelected) Green50 else Color.Transparent,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable(onClick = {
                onClick()
            }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = if (isSelected) Green50 else Grey30
        )
    }
}