package com.example.cointracker_android.feature.domain.use_case

import com.example.cointracker_android.feature.domain.model.AuthException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class SignInWithEmail(
    private val auth: FirebaseAuth
) {
    @Throws(AuthException::class)
    suspend operator fun invoke(email: String, password: String): FirebaseUser? {
        if (email.isBlank()) {
            throw AuthException("Email cannot be empty")
        }
        if (password.isBlank()) {
            throw AuthException("Password cannot be empty")
        }
        return suspendCancellableCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(auth.currentUser)
                    } else {
                        continuation.resume(null)
                    }
                }
        }
    }
}