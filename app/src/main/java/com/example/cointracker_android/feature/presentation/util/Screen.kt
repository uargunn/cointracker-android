package com.example.cointracker_android.feature.presentation.util

sealed class Screen(val route: String) {
    object CoinListScreen: Screen("coin_list_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
    object FavoritesScreen: Screen("favorites_screen")
    object ProfileScreen: Screen("profile_screen")
}