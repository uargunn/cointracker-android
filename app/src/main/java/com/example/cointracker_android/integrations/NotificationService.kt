package com.example.cointracker_android.integrations

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.cointracker_android.R
import com.example.cointracker_android.feature.presentation.MainActivity

class NotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(coinName: String) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_bitcoin)
            .setContentTitle("Price alert!")
            .setContentTitle("Current price has been changed for $coinName")
            .setContentIntent(activityPendingIntent)
            .build()
        notificationManager.notify(1, notification)
    }

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "coin_tracker_notification_channel"
    }
}