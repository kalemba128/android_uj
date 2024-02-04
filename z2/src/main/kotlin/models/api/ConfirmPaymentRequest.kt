package com.example.models.api

import models.CartProduct

data class ConfirmPaymentRequest (
    val paymentId: Int,
    val products : List<CartProduct>
)