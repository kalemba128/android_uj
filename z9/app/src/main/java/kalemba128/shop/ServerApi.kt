package kalemba128.shop

import kalemba128.shop.model.api.payment.CreatePaymentRequest
import kalemba128.shop.model.api.payment.CreatePaymentResponse
import kalemba128.shop.model.api.payment.GetProductsResponse
import retrofit2.Response
import retrofit2.http.*

interface ServerApi {

    @HTTP(method = "GET", path = "/products")
    suspend fun getProducts(): Response<GetProductsResponse>
    @HTTP(method = "POST", path = "/payment", hasBody = true)
    suspend fun createPayment(@Body req: CreatePaymentRequest): Response<CreatePaymentResponse>
}