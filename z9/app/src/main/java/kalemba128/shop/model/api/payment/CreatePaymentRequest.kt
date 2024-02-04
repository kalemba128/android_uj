package kalemba128.shop.model.api.payment

import kalemba128.shop.model.CartProduct


data class CreatePaymentRequest (
    val userId: Int,
    val products : List<CartProduct>
)
