package com.example.bolis.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CustomButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .width(40.dp)
            .background(Color.Green)
            .clickable(onClick = { })
    ) {
        Text(
            text = "Log In",
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.White
        )
    }
}