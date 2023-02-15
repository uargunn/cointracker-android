package com.example.cointracker_android.feature.domain.use_case.coin

import com.example.cointracker_android.feature.domain.model.CoinDetail
import com.example.cointracker_android.feature.domain.repository.CoinRepository

class AddToFavorites(
    private val coinRepository: CoinRepository
) {
    suspend operator fun invoke(coin: CoinDetail): Boolean {
        return coinRepository.addToFavorites(coin)
    }
}