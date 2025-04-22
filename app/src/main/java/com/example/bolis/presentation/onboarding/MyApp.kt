package com.example.bolis.presentation.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// ... other imports

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnboardingScreen {
                // Action to perform after onboarding (e.g., navigate to the main app screen)
                navController.navigate("main") {
                    popUpTo("onboarding") { inclusive = true }
                }
                // Also, you should likely save a flag in your app's settings (e.g. SharedPreferences)
                // to indicate that onboarding is complete and not show it again.
            }
        }
        composable("main") {
            // Your main app screen composable
        }
    }
}