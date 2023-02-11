package com.example.cointracker_android.di.domain

import com.example.cointracker_android.feature.domain.mapper.CoinMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideCoinMapper() : CoinMapper {
        return CoinMapper()
    }
}