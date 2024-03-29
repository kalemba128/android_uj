package database

import com.example.models.PaymentProducts
import com.example.models.Payments
import com.example.models.Users
import models.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./build/db"
        val database = Database.connect(jdbcURL, driverClassName)
        transaction(database) {
            SchemaUtils.create(Categories)
            SchemaUtils.create(Products)
            SchemaUtils.create(Users)
            SchemaUtils.create(Payments)
            SchemaUtils.create(PaymentProducts)
            runBlocking {}
        }
    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}