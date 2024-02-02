package database.dao.payment

import com.example.models.Payment
interface DAOPaymentFacade {
    suspend fun getPaymentById(id: Int): Payment?
    suspend fun getPaymentsByUserId(userId: Int): List<Payment>
    suspend fun getAllPayments(): List<Payment>
    suspend fun createPayment(payment: Payment): Payment
}