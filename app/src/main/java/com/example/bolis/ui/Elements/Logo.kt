package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bolis.R

@Preview
@Composable
fun Logo () {
    Image(
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = "show",
        Modifier.height(100.dp)
    )
}