package com.example.cointracker_android.feature.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.cointracker_android.feature.domain.use_case.coin.CoinUseCases
import com.example.cointracker_android.feature.worker.PriceTrackingWorker
import com.example.cointracker_android.feature.worker.PriceTrackingWorker.Companion.KEY_COIN_ID
import com.example.cointracker_android.feature.worker.PriceTrackingWorker.Companion.KEY_COIN_NAME
import com.example.cointracker_android.feature.worker.PriceTrackingWorker.Companion.KEY_COIN_PRICE
import com.example.cointracker_android.feature.worker.PriceTrackingWorker.Companion.KEY_WORK_NAME
import com.example.cointracker_android.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinUseCases: CoinUseCases,
    private val savedStateHandle: SavedStateHandle,
    private val workManager: WorkManager,
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    private val _loadingProgressState = mutableStateOf(false)
    val loadingProgressState: State<Boolean> = _loadingProgressState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    internal val outputWorkInfoItems: LiveData<List<WorkInfo>> =
        workManager.getWorkInfosForUniqueWorkLiveData(KEY_WORK_NAME)

    private val _curPrice = mutableStateOf(0.0)
    val curPrice: State<Double> = _curPrice

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
                    val isSuccess = coinUseCases.addToFavorites(state.value.coinDetail!!)
                    if (isSuccess) {
                        _eventFlow.emit(UiEvent.ShowSnackbarAction("Added to Your Favorites!"))
                    }
                    _loadingProgressState.value = false
                }
            }
            is CoinDetailEvent.AddPriceTracker -> {
                val inputData = Data.Builder()
                    .putString(KEY_COIN_ID, state.value.coinDetail?.id.orEmpty())
                    .putString(KEY_COIN_NAME, state.value.coinDetail?.name.orEmpty())
                    .putDouble(KEY_COIN_PRICE, state.value.coinDetail?.currentPrice ?: -1.0)
                    .build()
                val workRequest = PeriodicWorkRequestBuilder<PriceTrackingWorker>(
                    repeatInterval = 15,
                    TimeUnit.MINUTES
                ).setInputData(inputData).build()
                workManager.enqueueUniquePeriodicWork(
                    KEY_WORK_NAME,
                    ExistingPeriodicWorkPolicy.KEEP,
                    workRequest
                )
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.ShowSnackbar("You will receive a notification when the current price changes!"))
                }
            }
            is CoinDetailEvent.UpdatePrice -> {
                _curPrice.value = event.price
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
                    _curPrice.value = apiResult.data
                        ?.firstOrNull()
                        ?.currentPrice
                        ?: 0.0
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
        data class ShowSnackbarAction(val message: String) : UiEvent()
        data class ShowSnackbar(val message: String) : UiEvent()
    }
}