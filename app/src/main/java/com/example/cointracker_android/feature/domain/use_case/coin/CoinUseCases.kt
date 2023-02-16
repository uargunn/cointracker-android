package com.example.cointracker_android.feature.domain.use_case.coin

data class CoinUseCases(
    val getCoins: GetCoins,
    val getCoinDetailById: GetCoinDetailById,
    val addToFavorites: AddToFavorites,
    val getFavoriteCoins: GetFavoriteCoins,
    val removeFavoriteCoin: RemoveFavoriteCoin
)