package com.example.bolis.ui.Elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun CustomBackButton(modifier: Modifier = Modifier, name: String = "Back", onClick: () -> Unit = {}) {
    Row (
        modifier = modifier
            .height(24.dp)
            .clickable(onClick = { onClick() }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
            tint = Dark50
        )
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            fontFamily = fontFamily,
            color = Dark50
        )
    }
}