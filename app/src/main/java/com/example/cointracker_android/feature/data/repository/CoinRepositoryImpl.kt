package com.example.cointracker_android.feature.data.repository

import com.example.cointracker_android.feature.data.local.CoinDao
import com.example.cointracker_android.feature.data.remote.CoinGeckoApi
import com.example.cointracker_android.feature.domain.mapper.CoinMapper
import com.example.cointracker_android.feature.domain.model.Coin
import com.example.cointracker_android.feature.domain.repository.CoinRepository
import com.example.cointracker_android.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CoinRepositoryImpl(
    private val api: CoinGeckoApi,
    private val dao: CoinDao,
    private val coinMapper: CoinMapper
) : CoinRepository {

    override fun getCoins(query: String): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())

        val coins = dao.getCoins(query)

        try {
            if (coins.isEmpty()) {
                val remoteCoins = api.getCoins()
                dao.insertCoins(remoteCoins.map { coinMapper.map(it) })
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = ":( Something went wrong!",
                    data = coins
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = ":( Check your internet connection",
                    data = coins
                )
            )
        }

        emit(Resource.Success(data = dao.getCoins(query)))
    }
}