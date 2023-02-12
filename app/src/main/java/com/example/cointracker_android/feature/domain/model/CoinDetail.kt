package com.example.cointracker_android.feature.domain.model

data class CoinDetail(
    var id : String,
    var symbol : String,
    var name : String,
    var image : String,
    var currentPrice : Double,
    var marketCap : Double,
    var marketCapRank : String,
    var fullyDilutedValuation : String,
    var totalVolume : Double,
    var high24h : Double,
    var low24h : Double,
    var priceChange24h : Double,
    var priceChangePercentage24h : Double,
    var marketCapChange24h : Double,
    var marketCapChangePercentage24h : Double,
    var circulatingSupply : Double,
    var totalSupply : Double,
    var maxSupply : String,
    var ath : Double,
    var athChangePercentage : Double,
    var athDate : String,
    var atl : Double,
    var atlChangePercentage : Double,
    var atlDate : String,
    var lastUpdated : String
)