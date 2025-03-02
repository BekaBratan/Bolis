package com.example.bolis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
            BolisTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = LogInScreen
                ) {
                    composable<LogInScreen> {
                        LogInPage(navController = navController)
                    }
                    composable<SignUpScreen>{
                        SignUpPage(navController = navController)
                    }
                    composable<RegCodeScreen>{
                        ConfirmationCodePage(codeState = CodeState.REGISTRATION, navController = navController)
                    }
                    composable<RecPassScreen>{
                        PasswordRecoveryPage(isSetNewPassword = PasswordRecoveryState.PHONE_NUMBER, navController = navController)
                    }
                    composable<PassCodeScreen>{
                        ConfirmationCodePage(codeState = CodeState.PASSWORD, navController = navController)
                    }
                    composable<NewPassScreen>{
                        PasswordRecoveryPage(isSetNewPassword = PasswordRecoveryState.NEW_PASSWORD, navController = navController)
                    }
                    composable<SuccessPassScreen>{
                        PasswordRecoveryPage(isSetNewPassword = PasswordRecoveryState.SUCCESS, navController = navController)
                    }
                    composable<HomeScreen>{
                        HomePage()
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

@Serializable
object HomeScreen

