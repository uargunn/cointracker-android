package com.example.cointracker_android

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoinTrackerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initFirebase()
    }
    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }
}