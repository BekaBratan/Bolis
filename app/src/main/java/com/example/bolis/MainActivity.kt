package com.example.bolis

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bolis.data.CodeState
import com.example.bolis.data.PasswordRecoveryState
import com.example.bolis.data.api.systemLanguageChange
import com.example.bolis.presentation.auth.ConfirmationCodePage
import com.example.bolis.presentation.auth.LogInPage
import com.example.bolis.presentation.auth.PasswordRecoveryPage
import com.example.bolis.presentation.auth.SignUpPage
import com.example.bolis.ui.theme.BolisTheme
import com.example.bolis.ui.theme.White50
import com.example.bolis.utils.SharedProvider
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            fun navigateToHomeActivity() {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }

            BolisTheme {
                Scaffold(
                    containerColor = White50
                ) { innerPadding ->
                    systemLanguageChange(LocalContext.current, SharedProvider(LocalContext.current).getLanguage())
                    NavHost(
                        navController = navController,
                        startDestination = LogInScreen,
                        modifier = Modifier
                            .padding(innerPadding),
                    ) {
                        composable<LogInScreen> { backStackEntry ->
                            LogInPage(
                                createButtonClicked = { navController.navigate(SignUpScreen) },
                                forgotButtonClicked = { navController.navigate(RecPassScreen) },
                                nextButtonClicked = { navigateToHomeActivity() }
                            )
                        }
                        composable<SignUpScreen>{ backStackEntry ->
                            SignUpPage(
                                backButtonClicked = { navController.popBackStack() },
                                nextButtonClicked = { navController.navigate(RegCodeScreen) }
                            )
                        }
                        composable<RegCodeScreen>{ backStackEntry ->
                            ConfirmationCodePage(
                                codeState = CodeState.REGISTRATION,
                                backButtonClicked = { navController.popBackStack() },
                                nextButtonClicked = { navigateToHomeActivity() }
                            )
                        }
                        composable<RecPassScreen>{ backStackEntry ->
                            PasswordRecoveryPage(
                                isSetNewPassword = PasswordRecoveryState.PHONE_NUMBER,
                                backButtonClicked = { navController.popBackStack() },
                                nextButtonClicked = { navController.navigate(PassCodeScreen) },
                            )
                        }
                        composable<PassCodeScreen>{ backStackEntry ->
                            ConfirmationCodePage(
                                codeState = CodeState.PASSWORD,
                                backButtonClicked = { navController.popBackStack() },
                                nextButtonClicked = { navController.navigate(NewPassScreen) }
                            )
                        }
                        composable<NewPassScreen>{ backStackEntry ->
                            PasswordRecoveryPage(
                                isSetNewPassword = PasswordRecoveryState.NEW_PASSWORD,
                                backButtonClicked = { navController.popBackStack() },
                                nextButtonClicked = { navController.navigate(SuccessPassScreen) },
                            )
                        }
                        composable<SuccessPassScreen>{ backStackEntry ->
                            PasswordRecoveryPage(
                                isSetNewPassword = PasswordRecoveryState.SUCCESS,
                                backButtonClicked = { navController.popBackStack() },
                                nextButtonClicked = { navController.navigate(LogInScreen) },
                            )
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

