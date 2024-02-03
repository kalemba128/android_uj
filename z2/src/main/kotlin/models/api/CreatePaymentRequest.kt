package com.example.models.api

import com.example.models.PaymentProduct

data class CreatePaymentRequest (val products : List<PaymentProduct>)