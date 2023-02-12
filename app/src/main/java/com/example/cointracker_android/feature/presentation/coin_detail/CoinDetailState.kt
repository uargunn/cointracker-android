package com.example.cointracker_android.feature.presentation.coin_detail

import com.example.cointracker_android.feature.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val errMessage: String = ""
)