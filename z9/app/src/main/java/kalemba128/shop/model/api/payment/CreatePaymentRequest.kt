package kalemba128.shop.model.api.payment

import kalemba128.shop.model.CartProduct

data class CreatePaymentRequest (val products : List<CartProduct>)