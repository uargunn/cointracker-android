package com.example.cointracker_android.feature.presentation.favorite_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cointracker_android.feature.domain.use_case.coin.CoinUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val coinUseCases: CoinUseCases
): ViewModel() {

    private val _state = mutableStateOf(FavoriteListState())
    val state: State<FavoriteListState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _loadingProgressState = mutableStateOf(false)
    val loadingProgressState: State<Boolean> = _loadingProgressState

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
                errMessage = "No results"
            )
        }
    }

    fun removeFavoriteCoin(coinId: String) = viewModelScope.launch {
        _loadingProgressState.value = true
        val resultMessage = coinUseCases.removeFavoriteCoin(coinId)
        if (resultMessage != null) {
            _eventFlow.emit(UiEvent.ShowSnackbar(resultMessage))
            getFavoriteCoins()
        } else {
            _eventFlow.emit(UiEvent.ShowSnackbar("An unexpected error occurred"))
        }
        _loadingProgressState.value = false
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
    }
}