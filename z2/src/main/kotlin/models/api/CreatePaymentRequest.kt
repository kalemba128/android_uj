package com.example.models.api

import models.CartProduct

data class CreatePaymentRequest (
    val userId: Int,
    val products : List<CartProduct>
)
