package database.dao.payment_product

import com.example.models.PaymentProduct

interface DAOPaymentProductFacade {
    suspend fun getProductById(id: Int): PaymentProduct?
    suspend fun getProductsByPaymentId(paymentId: Int): List<PaymentProduct>
    suspend fun getAllProducts(): List<PaymentProduct>
    suspend fun createProduct(paymentId: Int,productId: Int, quantity: Int,total: Double): PaymentProduct
}