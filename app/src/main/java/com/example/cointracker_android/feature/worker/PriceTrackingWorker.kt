package com.example.cointracker_android.feature.worker

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.cointracker_android.R
import com.example.cointracker_android.feature.domain.use_case.coin.CoinUseCases
import com.example.cointracker_android.integrations.NotificationService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlin.random.Random

@HiltWorker
class PriceTrackingWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val coinUseCases: CoinUseCases
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val coinId = inputData.getString(KEY_COIN_ID).orEmpty()
        val coinName = inputData.getString(KEY_COIN_NAME).orEmpty()
        val coinPrice = inputData.getDouble(KEY_COIN_PRICE, -1.0)

        val priceResult = coinUseCases.getCurrentPrice(coinId)
        if (priceResult != coinPrice && coinPrice != -1.0)
            showNotification(coinName)
        return if (priceResult != null) {
            Result.success(workDataOf(KEY_COIN_PRICE to priceResult))
        } else {
            Result.failure()
        }
    }

    private fun showNotification(coinName: String) {
        val service = NotificationService(context)
        service.showNotification(coinName)
    }

    companion object {
        const val KEY_COIN_ID = "COIN_ID"
        const val KEY_COIN_NAME = "COIN_NAME"
        const val KEY_COIN_PRICE = "COIN_PRICE"
        const val KEY_WORK_NAME = "BTC_PRICE_TRACKING"
    }
}