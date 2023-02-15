package com.example.cointracker_android.feature.presentation.util

sealed class Screen(val route: String) {
    object CoinListScreen: Screen("coin_list_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
    object FavoriteListScreen: Screen("favorite_list_screen")
    object LoginScreen: Screen("login_screen")
}