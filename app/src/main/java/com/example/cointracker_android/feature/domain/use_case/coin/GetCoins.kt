package com.example.cointracker_android.feature.domain.use_case.coin

import com.example.cointracker_android.feature.domain.model.Coin
import com.example.cointracker_android.feature.domain.repository.CoinRepository
import com.example.cointracker_android.util.Resource
import kotlinx.coroutines.flow.Flow
class GetCoins(
    private val repository: CoinRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Coin>>> {
        return repository.getCoins(query)
    }
}