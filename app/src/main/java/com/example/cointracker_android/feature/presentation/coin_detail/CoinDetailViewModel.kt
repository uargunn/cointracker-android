package com.example.cointracker_android.feature.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cointracker_android.feature.domain.use_case.coin.CoinUseCases
import com.example.cointracker_android.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinUseCases: CoinUseCases,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    private val _loadingProgressState = mutableStateOf(false)
    val loadingProgressState: State<Boolean> = _loadingProgressState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("coinId")?.let {
            getCoinDetailById(it)
        }
    }

    fun onEvent(event: CoinDetailEvent) {
        when (event) {
            is CoinDetailEvent.AddToFavorite -> {
                _loadingProgressState.value = true
                viewModelScope.launch {
                    val isSuccess  = coinUseCases.addToFavorites(state.value.coinDetail!!)
                    if (isSuccess) {
                        _eventFlow.emit(UiEvent.ShowSnackbar("Added to Your Favorites"))
                    }
                    _loadingProgressState.value = false
                }
            }
        }
    }

    private fun getCoinDetailById(id: String) {
        coinUseCases.getCoinDetailById(id).onEach { apiResult ->
            _state.value = when (apiResult) {
                is Resource.Loading -> {
                    state.value.copy(
                        isLoading = true,
                        coinDetail = null,
                        errMessage = ""
                    )
                }
                is Resource.Error -> {
                    state.value.copy(
                        isLoading = false,
                        coinDetail = null,
                        errMessage = apiResult.message.orEmpty()
                    )
                }
                is Resource.Success -> {
                    state.value.copy(
                        isLoading = false,
                        coinDetail = apiResult.data
                            ?.firstOrNull(),
                        errMessage = ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
    }
}