package com.example.cointracker_android.feature.domain.use_case.auth

import com.example.cointracker_android.feature.domain.model.AuthException
import com.example.cointracker_android.feature.domain.model.FirebaseAuthResult
import com.example.cointracker_android.feature.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class SignUpWithEmail(
    private val repository: AuthRepository
) {
    @Throws(AuthException::class)
    suspend operator fun invoke(email: String, password: String): FirebaseAuthResult {
        if (email.isBlank()) {
            throw AuthException("Email cannot be empty")
        }
        if (password.isBlank()) {
            throw AuthException("Password cannot be empty")
        }
        return repository.signUpWithEmail(email, password)
    }
}