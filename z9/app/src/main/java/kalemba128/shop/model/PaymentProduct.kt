package kalemba128.shop.model


data class PaymentProduct(
    val id: Int,
    val paymentId: Int,
    val productId: Int,
    val quantity: Int,
    val price: Double,
    val total: Double,
)