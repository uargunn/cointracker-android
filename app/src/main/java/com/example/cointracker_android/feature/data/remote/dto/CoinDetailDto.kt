package com.example.cointracker_android.feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    @SerializedName("id")
    var id : String?,
    @SerializedName("symbol")
    var symbol : String?,
    @SerializedName("name")
    var name : String?,
    @SerializedName("image")
    var image : String?,
    @SerializedName("current_price")
    var currentPrice : Double?,
    @SerializedName("market_cap")
    var marketCap : Double?,
    @SerializedName("market_cap_rank")
    var marketCapRank : String?,
    @SerializedName("fully_diluted_valuation")
    var fullyDilutedValuation : String?,
    @SerializedName("total_volume")
    var totalVolume : Double?,
    @SerializedName("high_24h")
    var high24h : Double?,
    @SerializedName("low_24h")
    var low24h : Double?,
    @SerializedName("price_change_24h")
    var priceChange24h : Double?,
    @SerializedName("price_change_percentage_24h")
    var priceChangePercentage24h : Double?,
    @SerializedName("market_cap_change_24h")
    var marketCapChange24h : Double?,
    @SerializedName("market_cap_change_percentage_24h")
    var marketCapChangePercentage24h : Double?,
    @SerializedName("circulating_supply")
    var circulatingSupply : Double?,
    @SerializedName("total_supply")
    var totalSupply : Double?,
    @SerializedName("max_supply")
    var maxSupply : String?,
    @SerializedName("ath")
    var ath : Double?,
    @SerializedName("ath_change_percentage")
    var athChangePercentage : Double?,
    @SerializedName("ath_date")
    var athDate : String?,
    @SerializedName("atl")
    var atl : Double?,
    @SerializedName("atl_change_percentage")
    var atlChangePercentage : Double?,
    @SerializedName("atl_date")
    var atlDate : String?,
    @SerializedName("last_updated")
    var lastUpdated : String?
)