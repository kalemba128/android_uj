package com.example.services

import com.example.models.PaymentProduct
import com.stripe.Stripe
import com.stripe.model.Customer
import com.stripe.model.EphemeralKey
import com.stripe.model.PaymentIntent
import com.stripe.param.CustomerCreateParams
import com.stripe.param.EphemeralKeyCreateParams
import com.stripe.param.PaymentIntentCreateParams

class StripeService {
    fun createPayment(products : List<PaymentProduct>) {
        Stripe.apiKey = System.getenv("STRIPE_API_KEY")

        val total = products.sumOf { it.total }.toLong()

        val customerParams = CustomerCreateParams.builder().build()
        val customer = Customer.create(customerParams)

        val ephemeralKeyParams = EphemeralKeyCreateParams.builder()
            .setCustomer(customer.id)
            .build()

        val ephemeralKey = EphemeralKey.create(ephemeralKeyParams)

        val intentParams = PaymentIntentCreateParams.builder()
            .setCustomer(customer.id)
            .setAmount(total)
            .setCurrency("pln")
            .addAllPaymentMethodType(listOf("card", "ideal"))
            .build()

        val intent = PaymentIntent.create(intentParams)

    }
}