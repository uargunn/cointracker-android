package com.example.cointracker_android.feature.data.remote

import com.example.cointracker_android.feature.data.remote.dto.CoinDetailDto
import com.example.cointracker_android.feature.data.remote.dto.CoinDto
import com.example.cointracker_android.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("coins/list")
    suspend fun getCoins() : List<CoinDto>

    @GET("coins/markets")
    suspend fun getCoinDetail(
        @Query("ids") id: String,
        @Query("vs_currency") currency: String = Constants.Defaults.SWIFT_CODE,
    ) : List<CoinDetailDto>
}