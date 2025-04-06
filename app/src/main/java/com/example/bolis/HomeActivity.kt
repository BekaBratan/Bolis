package com.example.bolis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bolis.NavDestination.ChatScreen
import com.example.bolis.NavDestination.FavouriteScreen
import com.example.bolis.NavDestination.GiveScreen
import com.example.bolis.NavDestination.MarketScreen
import com.example.bolis.NavDestination.ProfileScreen
import com.example.bolis.ProfileDestination.ChangePasswordScreen
import com.example.bolis.ProfileDestination.ConfirmedScreen
import com.example.bolis.ProfileDestination.DeleteAccountScreen
import com.example.bolis.ProfileDestination.EditProfileScreen
import com.example.bolis.ProfileDestination.SupportScreen
import com.example.bolis.presentation.donate.AddItemPage
import com.example.bolis.presentation.home.ChatPage
import com.example.bolis.presentation.home.FavouritePage
import com.example.bolis.presentation.home.GivePage
import com.example.bolis.presentation.home.MarketPage
import com.example.bolis.presentation.profile.ChangePasswordPage
import com.example.bolis.presentation.profile.ConfirmedPage
import com.example.bolis.presentation.profile.DeleteAccountPage
import com.example.bolis.presentation.profile.ProfileEditPage
import com.example.bolis.presentation.profile.ProfilePage
import com.example.bolis.presentation.profile.SupportPage
import com.example.bolis.ui.theme.BolisTheme
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.White50
import kotlinx.serialization.Serializable

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val items = listOf(
                MarketScreen,
                FavouriteScreen,
                GiveScreen,
                ChatScreen,
                ProfileScreen
            )
            var selectedIndex by remember { mutableIntStateOf(0) }

            BolisTheme {
                Scaffold(
                    containerColor = White50,
                    bottomBar = {
                        NavigationBar(
                            contentColor = Green50,
                            containerColor = Color.White,
                            modifier = Modifier.border(BorderStroke(1.dp, Color.Gray))
                        ) {
                            items.forEachIndexed {index, screen ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painterResource(screen.icon),
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(text = screen.title) },
                                    selected = index == selectedIndex,
                                    onClick = {
                                        selectedIndex = index
                                        navController.navigate(screen) {
                                            popUpTo(navController.graph.startDestinationId)
                                            launchSingleTop = true
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = Green50,
                                        unselectedIconColor = Color.Gray,
                                        indicatorColor = Color.Transparent,
                                        selectedTextColor = Green50,
                                        unselectedTextColor = Color.Gray
                                    )
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        enterTransition = { fadeIn() },
                        exitTransition = { slideOutVertically() + shrinkVertically() + fadeOut() },
                        navController = navController,
                        startDestination = MarketScreen,
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        composable<MarketScreen>{ backStackEntry ->
                            MarketPage()
                        }
                        composable<FavouriteScreen>{ backStackEntry ->
                            FavouritePage()
                        }
                        composable<GiveScreen>{ backStackEntry ->
                            AddItemPage()
                        }
                        composable<ChatScreen>{ backStackEntry ->
                            ChatPage()
                        }
                        composable<ProfileScreen>{ backStackEntry ->
                            ProfilePage(
                                profileButtonClicked = { navController.navigate(EditProfileScreen) },
                                changePassButtonClicked = { navController.navigate(ChangePasswordScreen) },
                                supportButtonClicked = { navController.navigate(SupportScreen) },
                                deleteButtonClicked = { navController.navigate(DeleteAccountScreen) }
                            )
                        }
                        composable<EditProfileScreen>{ backStackEntry ->
                            ProfileEditPage(
                                backButtonClicked = { navController.popBackStack() }
                            )
                        }
                        composable<ChangePasswordScreen>{ backStackEntry ->
                            ChangePasswordPage(
                                backButtonClicked = { navController.popBackStack() }
                            )
                        }
                        composable<SupportScreen>{ backStackEntry ->
                            SupportPage(
                                backButtonClicked = { navController.popBackStack() }
                            )
                        }
                        composable<DeleteAccountScreen>{ backStackEntry ->
                            DeleteAccountPage(
                                backButtonClicked = { navController.popBackStack() }
                            )
                        }
                        composable<ConfirmedScreen>{ backStackEntry ->
                            ConfirmedPage(
                                backButtonClicked = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Serializable
sealed class NavDestination(val title: String, val icon: Int) {
    @Serializable
    object MarketScreen : NavDestination(title = "Market", icon = R.drawable.ic_market)
    @Serializable
    object FavouriteScreen : NavDestination(title = "Favourite", icon = R.drawable.ic_favourite)
    @Serializable
    object GiveScreen : NavDestination(title = "Give", icon = R.drawable.ic_give)
    @Serializable
    object ChatScreen : NavDestination(title = "Chat", icon = R.drawable.ic_chat)
    @Serializable
    object ProfileScreen : NavDestination(title = "Profile", icon = R.drawable.ic_profile)
}

@Serializable
sealed class ProfileDestination() {
    @Serializable
    object EditProfileScreen: ProfileDestination()
    @Serializable
    object ChangePasswordScreen: ProfileDestination()
    @Serializable
    object SupportScreen: ProfileDestination()
    @Serializable
    object DeleteAccountScreen: ProfileDestination()
    @Serializable
    object ConfirmedScreen: ProfileDestination()
}