package com.example.cointracker_android.di

import android.content.Context
import androidx.room.Room
import com.example.cointracker_android.feature.data.local.CoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    fun provideInMemoryDatabase(@ApplicationContext context: Context) =
        Room
            .inMemoryDatabaseBuilder(context, CoinDatabase::class.java)
            .build()

}