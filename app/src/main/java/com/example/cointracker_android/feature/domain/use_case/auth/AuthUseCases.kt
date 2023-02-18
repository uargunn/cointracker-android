package com.example.cointracker_android.feature.domain.use_case.auth

data class AuthUseCases(
    val signUpWithEmail: SignUpWithEmail,
    val getCurrentSession: GetCurrentSession,
)