package com.example.cointracker_android.feature.presentation.coin_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cointracker_android.feature.presentation.coin_list.components.CoinItem
import com.example.cointracker_android.feature.presentation.coin_list.components.SearchBar
import com.example.cointracker_android.feature.presentation.ui.common.navbar.BottomNavigationBar
import com.example.cointracker_android.feature.presentation.util.Screen

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                currentScreenId = Screen.CoinListScreen.route,
                onItemSelected = {
                    navController.navigate(it.route)
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchBar(
                text = "",
                hint = "Search Coin",
                onValueChange =  { searchQuery ->
                    viewModel.onSearch(searchQuery)
                },
                onFocusChange = {

                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.coins) { coin ->
                    CoinItem(
                        coin = coin,
                        onClick = {
                                  // TODO("navigate to detail screen")
                        },
                    )
                }
            }
        }
    }
}