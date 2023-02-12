package com.example.cointracker_android.di.domain

import com.example.cointracker_android.feature.domain.repository.CoinRepository
import com.example.cointracker_android.feature.domain.use_case.CoinUseCases
import com.example.cointracker_android.feature.domain.use_case.GetCoinDetailById
import com.example.cointracker_android.feature.domain.use_case.GetCoins
import com.example.cointracker_android.feature.domain.use_case.SignInWithEmail
import com.google.firebase.auth.FirebaseAuth
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
    fun provideSignInWithEmailUseCase(auth: FirebaseAuth): SignInWithEmail {
        return SignInWithEmail(auth)
    }
}