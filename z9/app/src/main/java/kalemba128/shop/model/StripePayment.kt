package com.example.models

data class StripePayment(
    val clientSecret: String,
    val ephemeralSecret: String,
    val customerId: String,
    val publishableKey: String,
)