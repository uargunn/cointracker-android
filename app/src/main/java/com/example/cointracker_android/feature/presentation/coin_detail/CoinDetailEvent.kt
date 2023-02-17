package com.example.cointracker_android.feature.presentation.coin_detail

sealed class CoinDetailEvent {
    object AddToFavorite: CoinDetailEvent()

    object AddPriceTracker: CoinDetailEvent()

    data class UpdatePrice(val price: Double) : CoinDetailEvent()
}