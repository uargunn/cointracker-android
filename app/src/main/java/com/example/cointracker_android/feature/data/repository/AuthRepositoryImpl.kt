package com.example.cointracker_android.feature.data.repository

import com.example.cointracker_android.feature.domain.model.FirebaseAuthResult
import com.example.cointracker_android.feature.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthRepository {

    override fun getCurrentSession(): FirebaseUser? = auth.currentUser

    override suspend fun signUpWithEmail(
        email: String,
        pass: String
    ): FirebaseAuthResult {
        return suspendCancellableCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(
                            FirebaseAuthResult(auth.currentUser, message = null)
                        )
                    } else {
                        continuation.resume(
                            FirebaseAuthResult(null, message = task.exception?.message.orEmpty())
                        )
                    }
                }
        }
    }
}