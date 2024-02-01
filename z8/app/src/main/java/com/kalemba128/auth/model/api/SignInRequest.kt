package com.kalemba128.auth.model.api

data class SignInRequest(
    val login: String,
    val password: String,
    val token: String?,
    val tokenProvider: String?,
)