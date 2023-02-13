package com.example.cointracker_android.feature.presentation.favorite_list

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cointracker_android.feature.presentation.ui.common.navbar.BottomNavigationBar
import com.example.cointracker_android.feature.presentation.util.Screen

@Composable
fun FavoriteListScreen(
    navController: NavController,
    viewModel: FavoriteListViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                currentScreenId = Screen.FavoriteListScreen.route,
                onItemSelected = {
                    navController.navigate(it.route)
                }
            )
        },
    ) {

    }
}