package com.example.cointracker_android.feature.domain.repository

import com.example.cointracker_android.feature.domain.model.Coin
import com.example.cointracker_android.feature.domain.model.CoinDetail
import com.example.cointracker_android.util.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun getCoins(query: String): Flow<Resource<List<Coin>>>

    fun getCoinDetailById(id: String): Flow<Resource<List<CoinDetail>>>

    suspend fun addToFavorites(coin: CoinDetail): Boolean

    suspend fun getFavoriteCoins(): List<CoinDetail>?

    suspend fun removeFavoriteCoin(coinId: String): String?
}