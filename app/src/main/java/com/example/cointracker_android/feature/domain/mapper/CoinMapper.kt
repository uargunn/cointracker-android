package com.example.cointracker_android.feature.domain.mapper

import com.example.cointracker_android.feature.data.remote.dto.CoinDetailDto
import com.example.cointracker_android.feature.data.remote.dto.CoinDto
import com.example.cointracker_android.feature.domain.model.Coin
import com.example.cointracker_android.feature.domain.model.CoinDetail
import com.example.cointracker_android.util.vo.orZero

class CoinMapper {
    fun map(dto: CoinDto): Coin {
        return Coin(
            symbol = dto.symbol.orEmpty(),
            name = dto.name.orEmpty(),
            id = dto.id.orEmpty()
        )
    }

    fun map(dto: CoinDetailDto): CoinDetail {
        return CoinDetail(
            id = dto.id.orEmpty(),
            symbol = dto.symbol.orEmpty(),
            name = dto.name.orEmpty(),
            image = dto.image.orEmpty(),
            currentPrice = dto.currentPrice ?: 0.0,
            marketCap = dto.marketCap ?: 0.0,
            marketCapRank = dto.marketCapRank.orEmpty(),
            fullyDilutedValuation = dto.fullyDilutedValuation.orEmpty(),
            totalVolume = dto.totalVolume ?: 0.0,
            high24h = dto.high24h ?: 0.0,
            low24h = dto.low24h ?: 0.0,
            priceChange24h = dto.priceChange24h ?: 0.0,
            priceChangePercentage24h = dto.priceChangePercentage24h ?: 0.0,
            marketCapChange24h = dto.marketCapChange24h ?: 0.0,
            marketCapChangePercentage24h = dto.marketCapChangePercentage24h ?: 0.0,
            circulatingSupply = dto.circulatingSupply ?: 0.0,
            totalSupply = dto.totalSupply ?: 0.0,
            maxSupply = dto.maxSupply.orEmpty(),
            ath = dto.ath ?: 0.0,
            athChangePercentage = dto.athChangePercentage ?: 0.0,
            athDate = dto.athDate.orEmpty(),
            atl = dto.atl ?: 0.0,
            atlChangePercentage = dto.atlChangePercentage ?: 0.0,
            atlDate = dto.atlDate.orEmpty(),
            lastUpdated = dto.lastUpdated.orEmpty()
        )
    }
}