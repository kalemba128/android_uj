package com.example.models.api

import kalemba128.shop.model.PaymentProduct

data class CreatePaymentRequest (val products : List<PaymentProduct>)