package com.example.cointracker_android.di.network

import com.example.cointracker_android.feature.data.remote.CoinGeckoApi
import com.example.cointracker_android.feature.data.remote.util.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        with(OkHttpClient.Builder()) {
            connectTimeout(CONNECT_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
            readTimeout(READ_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
            build()
        }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideCoinGeckoApi(retrofit: Retrofit): CoinGeckoApi =
        retrofit.create(CoinGeckoApi::class.java)
}