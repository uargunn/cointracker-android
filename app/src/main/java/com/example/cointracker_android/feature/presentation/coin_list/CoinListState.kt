package com.example.cointracker_android.feature.presentation.coin_list

import com.example.cointracker_android.feature.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val errMessage: String = ""
)