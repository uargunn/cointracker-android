package com.example.cointracker_android.feature.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cointracker_android.feature.presentation.coin_list.components.CoinItem
import com.example.cointracker_android.feature.presentation.coin_list.components.SearchBar
import com.example.cointracker_android.feature.presentation.ui.common.navbar.BottomNavigationBar
import com.example.cointracker_android.feature.presentation.ui.theme.GrassGreen
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
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(GrassGreen),
                    contentAlignment = Alignment.Center
                ) {
                    // region SearchBar
                    SearchBar(
                        text = viewModel.searchQuery.value,
                        hint = "Search Coin",
                        isHintVisible = viewModel.searchQuery.value.isBlank(),
                        onValueChange = viewModel::onSearch,
                        singleLine = true,
                        onFocusChange = {

                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(54.dp)
                    )
                    // endregion
                }

                // region CoinList
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(state.coins) { coin ->
                        CoinItem(
                            coin = coin,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                // endregion
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}