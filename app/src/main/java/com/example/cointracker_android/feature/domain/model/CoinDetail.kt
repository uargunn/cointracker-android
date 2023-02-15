package com.example.cointracker_android.feature.domain.model

data class CoinDetail(
    var id : String = "",
    var symbol : String = "",
    var name : String = "",
    var image : String = "",
    var currentPrice : Double = .0,
    var marketCap : Double = .0,
    var marketCapRank : String = "",
    var fullyDilutedValuation : String = "",
    var totalVolume : Double = .0,
    var high24h : Double = .0,
    var low24h : Double = .0,
    var priceChange24h : Double = .0,
    var priceChangePercentage24h : Double = .0,
    var marketCapChange24h : Double = .0,
    var marketCapChangePercentage24h : Double = .0,
    var circulatingSupply : Double = .0,
    var totalSupply : Double = .0,
    var maxSupply : String = "",
    var ath : Double = .0,
    var athChangePercentage : Double = .0,
    var athDate : String = "",
    var atl : Double = .0,
    var atlChangePercentage : Double = .0,
    var atlDate : String = "",
    var lastUpdated : String = ""
)