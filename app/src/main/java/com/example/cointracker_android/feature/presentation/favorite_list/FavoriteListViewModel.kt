package com.example.cointracker_android.feature.presentation.favorite_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cointracker_android.feature.domain.use_case.coin.CoinUseCases
import com.example.cointracker_android.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val coinUseCases: CoinUseCases
): ViewModel() {

    private val _state = mutableStateOf(FavoriteListState())
    val state: State<FavoriteListState> = _state

    init {
        getFavoriteCoins()
    }

    private fun getFavoriteCoins() = viewModelScope.launch {
        val favoriteCoinsResult = coinUseCases.getFavoriteCoins()
        _state.value = if (favoriteCoinsResult != null) {
            state.value.copy(
                isLoading = false,
                isEmpty = false,
                coins = favoriteCoinsResult,
                errMessage = ""
            )
        } else {
            state.value.copy(
                isLoading = false,
                isEmpty = true,
                coins = emptyList(),
                errMessage = "data not found"
            )
        }
    }
}