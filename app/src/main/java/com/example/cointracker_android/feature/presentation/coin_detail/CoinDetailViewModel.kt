package com.example.cointracker_android.feature.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import com.example.cointracker_android.feature.domain.use_case.CoinUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinUseCases: CoinUseCases,
    private val savedStateHandle: SavedStateHandle,
) {
    init {
        savedStateHandle.get<String>("coinId")?.let {

        }
    }
}