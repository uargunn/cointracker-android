package com.example.cointracker_android.feature.data.remote

import com.example.cointracker_android.feature.data.remote.dto.CoinDto
import retrofit2.http.GET

interface CoinGeckoApi {

    @GET("coins/list")
    suspend fun getCoins() : List<CoinDto>
}