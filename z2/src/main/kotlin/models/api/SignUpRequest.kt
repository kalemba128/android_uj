package com.example.models.api

data class SignUpRequest(
    val login: String,
    val password: String,
    val token: String?,
    val tokenProvider: String?
)