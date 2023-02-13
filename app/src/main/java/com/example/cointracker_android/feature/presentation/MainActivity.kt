package com.example.cointracker_android.feature.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cointracker_android.feature.presentation.coin_detail.CoinDetailScreen
import com.example.cointracker_android.feature.presentation.coin_list.CoinListScreen
import com.example.cointracker_android.feature.presentation.favorite_list.FavoriteListScreen
import com.example.cointracker_android.feature.presentation.login.LoginScreen
import com.example.cointracker_android.feature.presentation.ui.theme.CointrackerandroidTheme
import com.example.cointracker_android.feature.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            CointrackerandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(Screen.LoginScreen.route) {
                            LoginScreen(
                                onNavToHomePage = {
                                    navController.navigate(Screen.CoinListScreen.route) {
                                        popUpTo(Screen.LoginScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }

                        composable(Screen.CoinListScreen.route) {
                            CoinListScreen(navController = navController)
                        }

                        composable(
                            route = Screen.CoinDetailScreen.route +
                                    "?coinId={coinId}",
                            arguments = listOf(
                                navArgument(name = "coinId") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            CoinDetailScreen(navController = navController)
                        }

                        composable(Screen.FavoriteListScreen.route) {
                            FavoriteListScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}