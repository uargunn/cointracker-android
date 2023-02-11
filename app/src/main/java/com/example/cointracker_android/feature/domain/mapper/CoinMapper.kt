package com.example.cointracker_android.feature.domain.mapper

import com.example.cointracker_android.feature.data.remote.dto.CoinDto
import com.example.cointracker_android.feature.domain.model.Coin

class CoinMapper {
    fun map(entity: CoinDto) : Coin {
        return Coin(
            symbol = entity.symbol.orEmpty(),
            name = entity.name.orEmpty(),
            id = entity.id.orEmpty()
        )
    }
}