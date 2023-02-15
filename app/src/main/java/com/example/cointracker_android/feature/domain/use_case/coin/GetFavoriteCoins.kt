package com.example.cointracker_android.feature.domain.use_case.coin

import com.example.cointracker_android.feature.domain.model.CoinDetail
import com.example.cointracker_android.feature.domain.repository.CoinRepository

class GetFavoriteCoins(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() : List<CoinDetail>? {
        return repository.getFavoriteCoins()
    }
}