package kalemba128.network.api

import kalemba128.network.models.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("/categories")
    suspend fun getCategories(): Response<GetCategoriesResponse>

    @GET("/products")
    suspend fun getProducts(): Response<GetProductsResponse>

    @POST("/product")
    suspend fun addProduct(@Body product: Product): Response<String>

}