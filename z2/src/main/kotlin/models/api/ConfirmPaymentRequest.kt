package com.example.models.api

import models.CartProduct

data class ConfirmPaymentRequest (
    val userId: Int,
    val paymentId: Int,
    val products : List<CartProduct>
)