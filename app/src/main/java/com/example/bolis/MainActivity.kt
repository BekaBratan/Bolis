package com.example.bolis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bolis.data.CodeState
import com.example.bolis.data.PasswordRecoveryState
import com.example.bolis.presentation.ConfirmationCodePage
import com.example.bolis.presentation.HomePage
import com.example.bolis.presentation.LogInPage
import com.example.bolis.presentation.PasswordRecoveryPage
import com.example.bolis.presentation.SignUpPage
import com.example.bolis.ui.theme.BolisTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            BolisTheme {
                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = LogInScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<LogInScreen> { backStackEntry ->
                            LogInPage(navController = navController)
                        }
                        composable<SignUpScreen>{ backStackEntry ->
                            SignUpPage(navController = navController)
                        }
                        composable<RegCodeScreen>{ backStackEntry ->
                            ConfirmationCodePage(codeState = CodeState.REGISTRATION, navController = navController)
                        }
                        composable<RecPassScreen>{ backStackEntry ->
                            PasswordRecoveryPage(isSetNewPassword = PasswordRecoveryState.PHONE_NUMBER, navController = navController)
                        }
                        composable<PassCodeScreen>{ backStackEntry ->
                            ConfirmationCodePage(codeState = CodeState.PASSWORD, navController = navController)
                        }
                        composable<NewPassScreen>{ backStackEntry ->
                            PasswordRecoveryPage(isSetNewPassword = PasswordRecoveryState.NEW_PASSWORD, navController = navController)
                        }
                        composable<SuccessPassScreen>{ backStackEntry ->
                            PasswordRecoveryPage(isSetNewPassword = PasswordRecoveryState.SUCCESS, navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object LogInScreen

@Serializable
object SignUpScreen

@Serializable
object RegCodeScreen

@Serializable
object RecPassScreen

@Serializable
object PassCodeScreen

@Serializable
object NewPassScreen

@Serializable
object SuccessPassScreen

