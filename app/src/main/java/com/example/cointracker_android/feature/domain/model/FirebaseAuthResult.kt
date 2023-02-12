package com.example.cointracker_android.feature.domain.model

import com.google.firebase.auth.FirebaseUser

data class FirebaseAuthResult(
    val user: FirebaseUser?,
    val message: String?,
)
