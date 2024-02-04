package kalemba128.shop

import kalemba128.shop.model.api.payment.GetPaymentProductsRequest
import kalemba128.shop.model.api.payment.GetPaymentProductsResponse
import kalemba128.shop.model.api.payment.ConfirmPaymentRequest
import kalemba128.shop.model.api.payment.ConfirmPaymentResponse
import kalemba128.shop.model.api.payment.CreatePaymentRequest
import kalemba128.shop.model.api.payment.CreatePaymentResponse
import kalemba128.shop.model.api.payment.GetProductsResponse
import retrofit2.Response
import retrofit2.http.*

interface ServerApi {

    @HTTP(method = "GET", path = "/products")
    suspend fun getProducts(): Response<GetProductsResponse>
    @HTTP(method = "POST", path = "/createPayment", hasBody = true)
    suspend fun createPayment(@Body req: CreatePaymentRequest): Response<CreatePaymentResponse>

    @HTTP(method = "POST", path = "/confirmPayment", hasBody = true)
    suspend fun confirmPayment(@Body req: ConfirmPaymentRequest): Response<ConfirmPaymentResponse>

    @HTTP(method = "POST", path = "/paymentProducts", hasBody = true)
    suspend fun getPaymentProducts(@Body req: GetPaymentProductsRequest): Response<GetPaymentProductsResponse>
}