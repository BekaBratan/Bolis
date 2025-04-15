package com.example.bolis.presentation.profile

import androidx.compose.runtime.Composable
import com.example.bolis.ui.Elements.WebView

@Composable
fun MapPage() {
    WebView("https://www.google.com/maps/@43.2090441,76.7659705,13")
}