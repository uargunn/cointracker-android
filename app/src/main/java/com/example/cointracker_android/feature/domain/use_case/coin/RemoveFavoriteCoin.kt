package com.example.cointracker_android.feature.domain.use_case.coin

import com.example.cointracker_android.feature.domain.repository.CoinRepository

class RemoveFavoriteCoin(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(coinId: String): String? {
        return repository.removeFavoriteCoin(coinId)
    }
}