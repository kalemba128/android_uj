package kalemba128.shop.model.api.payment

import kalemba128.shop.model.CartProduct


data class ConfirmPaymentRequest (
    val paymentId: Int,
    val products : List<CartProduct>
)