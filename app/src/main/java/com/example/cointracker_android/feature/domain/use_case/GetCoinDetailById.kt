package com.example.cointracker_android.feature.domain.use_case

import com.example.cointracker_android.feature.domain.model.CoinDetail
import com.example.cointracker_android.feature.domain.repository.CoinRepository
import com.example.cointracker_android.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCoinDetailById(
    private val repository: CoinRepository
) {
    operator fun invoke(id: String): Flow<Resource<List<CoinDetail>>> {
        return repository.getCoinDetailById(id)
    }
}