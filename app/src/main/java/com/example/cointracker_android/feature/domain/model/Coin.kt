package com.example.cointracker_android.feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coin(
    val symbol: String,
    val name: String,
    @PrimaryKey val id: String,
)