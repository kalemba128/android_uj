package kalemba128.shop.model.api.payment

import kalemba128.shop.model.PaymentProduct


data class GetPaymentProductsResponse (val products: List<PaymentProduct>)