package com.example.cointracker_android.di.domain

import com.example.cointracker_android.feature.domain.repository.AuthRepository
import com.example.cointracker_android.feature.domain.repository.CoinRepository
import com.example.cointracker_android.feature.domain.use_case.auth.AuthUseCases
import com.example.cointracker_android.feature.domain.use_case.auth.GetCurrentSession
import com.example.cointracker_android.feature.domain.use_case.coin.CoinUseCases
import com.example.cointracker_android.feature.domain.use_case.coin.GetCoinDetailById
import com.example.cointracker_android.feature.domain.use_case.coin.GetCoins
import com.example.cointracker_android.feature.domain.use_case.auth.SignUpWithEmail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideCoinUseCases(repository: CoinRepository) : CoinUseCases {
        return CoinUseCases(
            getCoins = GetCoins(repository),
            getCoinDetailById = GetCoinDetailById(repository)
        )
    }
    @Provides
    @Singleton
    fun provideAuthUseCases(repository: AuthRepository) : AuthUseCases {
        return AuthUseCases(
            signUpWithEmail = SignUpWithEmail(repository),
            getCurrentSession = GetCurrentSession(repository)
        )
    }
}