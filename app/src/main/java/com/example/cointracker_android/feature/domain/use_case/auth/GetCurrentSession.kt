package com.example.cointracker_android.feature.domain.use_case.auth

import com.example.cointracker_android.feature.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class GetCurrentSession(
    private val repository: AuthRepository
) {
    operator fun invoke() : FirebaseUser?{
        return repository.getCurrentSession()
    }
}