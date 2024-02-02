package database.dao.payment_product

import com.example.models.PaymentProduct
import com.example.models.PaymentProducts
import database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class DAOPaymentProductFacadeImpl : DAOPaymentProductFacade {
    override suspend fun getProductById(id: Int): PaymentProduct? = dbQuery {
        PaymentProducts.select { PaymentProducts.id eq id }.map(::toPaymentProduct).firstOrNull()
    }

    override suspend fun getProductsByPaymentId(paymentId: Int): List<PaymentProduct> = dbQuery {
        PaymentProducts.select { PaymentProducts.paymentId eq paymentId }.map(::toPaymentProduct)
    }

    override suspend fun getAllProducts(): List<PaymentProduct> = dbQuery {
        PaymentProducts.selectAll().map(::toPaymentProduct)
    }

    override suspend fun createProduct(product: PaymentProduct): PaymentProduct = dbQuery {
        PaymentProducts.insert {
            it[paymentId] = product.paymentId
            it[productId] = product.productId
            it[quantity] = product.quantity
            it[total] = product.total
            it[price] = product.price
        }.resultedValues?.map(::toPaymentProduct)?.first()!!
    }

    private fun toPaymentProduct(row: ResultRow) = PaymentProduct(
        id = row[PaymentProducts.id],
        paymentId = row[PaymentProducts.paymentId],
        productId = row[PaymentProducts.productId],
        quantity = row[PaymentProducts.quantity],
        total = row[PaymentProducts.total],
        price = row[PaymentProducts.price]
    )
}

val daoPaymentProduct: DAOPaymentProductFacade = DAOPaymentProductFacadeImpl()