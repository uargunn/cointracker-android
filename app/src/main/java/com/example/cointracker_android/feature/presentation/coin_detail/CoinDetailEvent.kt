package com.example.cointracker_android.feature.presentation.coin_detail

sealed class CoinDetailEvent {
    object AddToFavorite: CoinDetailEvent()
}