package database.dao.payment

import com.example.models.Payment
import com.example.models.Payments
import database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.joda.time.DateTime
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class DAOPaymentFacadeImpl : DAOPaymentFacade {
    override suspend fun getPaymentById(id: Int): Payment? = dbQuery {
        Payments.select{Payments.id eq id}.map ( ::toPayment ).firstOrNull()
    }
    override suspend fun getPaymentsByUserId(userId: Int): List<Payment> = dbQuery{
        Payments.select{Payments.userId eq userId}.map ( ::toPayment )
    }

    override suspend fun getAllPayments(): List<Payment> = dbQuery {
        Payments.selectAll().map(::toPayment)
    }

    override suspend fun createPayment(userId: Int,amount: Double): Payment = dbQuery {
        Payments.insert {
            it[this.userId] = userId
            it[this.amount] = amount
            it[timestamp] = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now())
        }.resultedValues?.map(::toPayment)?.first()!!
    }
    private fun toPayment(row: ResultRow) = Payment(
        id = row[Payments.id],
        userId = row[Payments.userId],
        timestamp = row[Payments.timestamp],
        amount = row[Payments.amount],
    )
}

val daoPayment: DAOPaymentFacade = DAOPaymentFacadeImpl()