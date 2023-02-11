package com.example.cointracker_android.feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("name")
    val name: String?
)