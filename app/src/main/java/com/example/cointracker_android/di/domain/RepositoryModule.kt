package com.example.cointracker_android.di.domain

import com.example.cointracker_android.feature.data.local.CoinDatabase
import com.example.cointracker_android.feature.data.remote.CoinGeckoApi
import com.example.cointracker_android.feature.data.repository.AuthRepositoryImpl
import com.example.cointracker_android.feature.data.repository.CoinRepositoryImpl
import com.example.cointracker_android.feature.domain.mapper.CoinMapper
import com.example.cointracker_android.feature.domain.repository.AuthRepository
import com.example.cointracker_android.feature.domain.repository.CoinRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCoinRepository(
        api: CoinGeckoApi,
        db: CoinDatabase,
        coinMapper: CoinMapper,
        firestore: FirebaseFirestore,
        auth: FirebaseAuth
    ): CoinRepository {
        return CoinRepositoryImpl(
            api = api,
            dao = db.coinDao,
            coinMapper = coinMapper,
            firestore = firestore,
            auth = auth
        )
    }
    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(auth)
    }
}