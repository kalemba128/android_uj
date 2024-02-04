package kalemba128.shop.model


data class Order(
    val paymentId: Int,
    val products: List<PaymentProduct>,
    val productsCount: Int,
    val total : Double
)
