package com.example.cointracker_android.feature.presentation.favorite_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cointracker_android.R
import com.example.cointracker_android.feature.presentation.favorite_list.component.FavoriteCoinItem
import com.example.cointracker_android.feature.presentation.ui.common.LoadingDialog
import com.example.cointracker_android.feature.presentation.ui.common.navbar.BottomNavigationBar
import com.example.cointracker_android.feature.presentation.ui.theme.Dark
import com.example.cointracker_android.feature.presentation.ui.theme.White
import com.example.cointracker_android.feature.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoriteListScreen(
    navController: NavController,
    viewModel: FavoriteListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is FavoriteListViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    if (viewModel.loadingProgressState.value)
        LoadingDialog()

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
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // region CoinList
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(state.coins) { coin ->
                    FavoriteCoinItem(
                        coin = coin,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .shadow(2.dp, RoundedCornerShape(8.dp))
                            .background(White, RoundedCornerShape(8.dp)),
                        onItemClick = {
                            navController.navigate(
                                Screen.CoinDetailScreen.route +
                                        "?coinId=${coin?.id.orEmpty()}"
                            )
                        },
                        onDeleteClick = {
                            viewModel.removeFavoriteCoin(coinId = coin?.id.orEmpty())
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
            // endregion

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (state.isEmpty) {
                Text(
                    text = stringResource(id = R.string.no_results),
                    color = Dark.copy(.5f),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}