package com.example.bolis.presentation.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.bolis.ui.Elements.WebView

@Composable
fun MarketPage() {
    WebView("http://bolis.maisa.kz/home")
}