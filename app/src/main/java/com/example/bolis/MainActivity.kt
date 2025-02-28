package com.example.bolis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bolis.ui.theme.BolisTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BolisTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = FirstScreen
                ) {
                    composable<FirstScreen> {
                    }

                    composable<SecondScreen>{
                    }
                }
            }
        }
    }
}

@Serializable
object FirstScreen

@Serializable
data class SecondScreen(
    val id: Int,
    val name: String?
)
