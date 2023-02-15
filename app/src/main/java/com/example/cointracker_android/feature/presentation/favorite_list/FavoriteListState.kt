package com.example.cointracker_android.feature.presentation.favorite_list

import com.example.cointracker_android.feature.domain.model.CoinDetail

data class FavoriteListState(
    val isLoading: Boolean = true,
    val isEmpty: Boolean = false,
    val coins: List<CoinDetail?> = emptyList(),
    val errMessage: String = ""
)