package com.example.cointracker_android.feature.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cointracker_android.feature.domain.use_case.coin.CoinUseCases
import com.example.cointracker_android.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinUseCases: CoinUseCases
): ViewModel() {

    // To avoid creating a new flow each time.
    private var searchJob: Job? = null

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        onSearch(searchQuery.value)
    }

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            coinUseCases.getCoins(query)
                .onEach { result ->
                    _state.value = when (result) {
                        is Resource.Loading -> {
                            state.value.copy(
                                isEmpty = false,
                                isLoading = true,
                                coins = emptyList(),
                                errMessage = ""
                            )
                        }
                        is Resource.Error -> {
                            state.value.copy(
                                isEmpty = false,
                                isLoading = false,
                                coins = emptyList(),
                                errMessage = result.message.orEmpty()
                            )
                        }
                        is Resource.Success -> {
                            if (result.data.isNullOrEmpty()){
                                state.value.copy(
                                    isEmpty = true,
                                    isLoading = false,
                                    coins = emptyList(),
                                    errMessage = ""
                                )
                            } else {
                                state.value.copy(
                                    isEmpty = false,
                                    isLoading = false,
                                    coins = result.data,
                                    errMessage = ""
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}