package com.example.bolis

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.yandex.mapkit.MapKitFactory

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey("e6484f57-e059-4a2e-aadc-6bb59b0bd502")
        MapKitFactory.initialize(this)
    }
}