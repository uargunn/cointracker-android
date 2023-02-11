package com.example.cointracker_android.feature.presentation.ui.common.navbar.util

import com.example.cointracker_android.R
import com.example.cointracker_android.feature.presentation.util.Screen

sealed class BottomNavItem(
    val name: String,
    val route: String,
    val icon: Int
) {
    object Coins : BottomNavItem(
        name = "Coins",
        route = Screen.CoinListScreen.route,
        icon = R.drawable.ic_bitcoin
    )

    object Favorites : BottomNavItem(
        name = "Favorites",
        route = "category_screen",
        icon = R.drawable.ic_favorites
    )

    object Profile : BottomNavItem(
        name = "Profile",
        route = "profile_screen",
        icon = R.drawable.ic_profile
    )
}