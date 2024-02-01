package com.kalemba128.auth.model

data class User(
    val id: Int,
    val login: String,
    val password: String,
    val token: String?,
    val tokenProvider: String?,
)