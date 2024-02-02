package com.example.models

import models.Products
import org.jetbrains.exposed.sql.*

data class PaymentProduct(
    val id: Int,
    val paymentId: Int,
    val productId: Int,
    val quantity: Int,
    val price: Double,
    val total: Double,
)

object PaymentProducts : Table() {
    val id = integer("id").autoIncrement()
    val paymentId = reference("paymentId", Payments.id, ReferenceOption.NO_ACTION, ReferenceOption.NO_ACTION)
    val productId = reference("productId", Products.id, ReferenceOption.NO_ACTION, ReferenceOption.NO_ACTION)
    val quantity = integer("quantity")
    val price = double("price")
    val total = double("total")
    override val primaryKey = PrimaryKey(id)
}