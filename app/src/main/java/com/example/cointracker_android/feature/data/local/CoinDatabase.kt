package com.example.cointracker_android.feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cointracker_android.feature.domain.model.Coin

@Database(
    entities = [Coin::class],
    version = 1
)
abstract class CoinDatabase : RoomDatabase() {
    abstract val coinDao: CoinDao

    companion object {
        const val DATABASE_NAME = "coins_db"
    }
}