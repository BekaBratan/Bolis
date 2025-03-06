package com.example.bolis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bolis.presentation.HomePage
import com.example.bolis.ui.theme.BolisTheme
import kotlinx.serialization.Serializable

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val items = listOf(
                HomeScreen
            )
            var selectedIndex by remember { mutableIntStateOf(0) }

            BolisTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            contentColor = Color.Green,
                        ) {
                            items.forEachIndexed {index, screen ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painterResource(R.drawable.ic_back),
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text("") },
                                    selected = index == selectedIndex,
                                    onClick = {
                                        selectedIndex = index
                                        navController.navigate(screen) {
                                            popUpTo(navController.graph.startDestinationId)
                                            launchSingleTop = true
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = Color.Green,
                                        unselectedIconColor = Color.Gray,
                                        indicatorColor = Color.Transparent
                                    )
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = HomeScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<HomeScreen>{ backStackEntry ->
                            HomePage()
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object HomeScreen

