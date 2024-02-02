package com.example.models

import org.jetbrains.exposed.sql.*

data class Payment(
    val id: Int,
    val userId: Int,
    val amount: Double,
    val timestamp: String,
)

object Payments : Table() {
    val id = integer("id").autoIncrement()
    val userId = reference("userId", Users.id,ReferenceOption.NO_ACTION, ReferenceOption.NO_ACTION)
    val timestamp = varchar("timestamp", 64)
    val amount = double("amount").default(0.0)
    override val primaryKey = PrimaryKey(id)
}