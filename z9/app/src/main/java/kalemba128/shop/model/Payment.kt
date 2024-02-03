package kalemba128.shop.model

data class Payment(
    val id: Int,
    val userId: Int,
    val amount: Double,
    val timestamp: String,
)