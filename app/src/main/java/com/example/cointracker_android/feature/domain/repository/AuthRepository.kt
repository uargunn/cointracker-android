package com.example.cointracker_android.feature.domain.repository

import com.example.cointracker_android.feature.domain.model.FirebaseAuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    fun getCurrentSession(): FirebaseUser?

    suspend fun signUpWithEmail(
        email: String,
        pass: String,
    ): FirebaseAuthResult
}