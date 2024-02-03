package com.example.models.api

import models.CartProduct

data class CreatePaymentRequest (val products : List<CartProduct>)