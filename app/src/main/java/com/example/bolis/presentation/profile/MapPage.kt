package com.example.bolis.presentation.profile

import androidx.compose.runtime.Composable
import com.example.bolis.data.api.navBarStateChange

@Composable
fun MapPage() {
    navBarStateChange(false)
    MapWithUserLocation()
}