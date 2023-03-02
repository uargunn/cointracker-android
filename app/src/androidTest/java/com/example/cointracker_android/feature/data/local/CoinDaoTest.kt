package com.example.cointracker_android.feature.data.local

import com.example.cointracker_android.di.AppModule
import com.example.cointracker_android.feature.domain.model.Coin
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class CoinDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var database: CoinDatabase
    private lateinit var dao: CoinDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.coinDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertCoins(): Unit = runBlocking {
        val testCoin1 = Coin(symbol = "test-1", name = "test-1", id = "test-1")
        val testCoin2 = Coin(symbol = "test-2", name = "test-2", id = "test-2")
        dao.insertCoins(listOf(testCoin1, testCoin2))

        val allCoins = dao.getCoins("")
        assertThat(allCoins.contains(testCoin1))
    }
}