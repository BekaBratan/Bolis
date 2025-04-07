package com.example.bolis.ui.Elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun OptionsButton(
    options: List<String> = listOf("Button", "Button"),
    selectedIndex: Int = 0,
    onClick: (Int) -> Unit = {}
) {
    LazyRow(
        modifier = Modifier
            .height(32.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(White40),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(options.size) { index ->
            OptionsButtonItem(
                name = options[index],
                isSelected = index == selectedIndex,
                onClick = {
                    onClick(index)
                }
            )
            if (index!=options.size-1) {
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .background(Grey30)
                )
            }
        }
    }
}

@Preview
@Composable
fun OptionsButtonItem(
    name: String = "Button",
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(7.dp)
            .clickable(onClick = {
                onClick()
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .width(10.dp)
                .height(10.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(color = if (isSelected) Green50 else Grey30)
        )
        Text(
            text = name,
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = if (isSelected) Green50 else Grey30
        )
    }
}