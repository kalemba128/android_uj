package kalemba128.shop.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kalemba128.shop.RetrofitHelper
import kalemba128.shop.ServerApi
import kalemba128.shop.model.Product
import androidx.lifecycle.*
import com.example.models.StripePayment
import kalemba128.shop.model.api.payment.GetPaymentProductsRequest
import kalemba128.shop.model.CartProduct
import kalemba128.shop.model.Order
import kalemba128.shop.model.PaymentProduct
import kalemba128.shop.model.api.payment.ConfirmPaymentRequest
import kalemba128.shop.model.api.payment.CreatePaymentRequest
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var api = RetrofitHelper.getInstance(null).create(ServerApi::class.java)

    var productsState: MutableLiveData<List<Product>> = MutableLiveData()
    var cartProductsState: MutableLiveData<List<CartProduct>> = MutableLiveData()
    var ordersState: MutableLiveData<List<Order>> = MutableLiveData()


    val products get() = productsState.value ?: listOf()
    val cartProducts get() = cartProductsState.value ?: listOf()
    val orders get() = ordersState.value ?: listOf()


    fun getProducts() {
        viewModelScope.launch {
            val response = api.getProducts()
            if (response.isSuccessful) {
                val body = response.body()!!
                productsState.postValue(body.products)
            }
        }
    }

    fun getOrders() {
        viewModelScope.launch {
            val response = api.getPaymentProducts(GetPaymentProductsRequest(userId = 1))
            if (response.isSuccessful) {
                val body = response.body()!!
                var orders: List<Order> = listOf()
                val payments = body.products
                val data = mutableMapOf<Int, List<PaymentProduct>>()

                for (payment in payments) {
                    val current = data[payment.paymentId] ?: listOf()
                    data[payment.paymentId] = current.plus(payment)
                }

                for (row in data) {
                    val products = row.value
                    val total = products.sumOf { it.total }
                    val order = Order(
                        paymentId = row.key, products = row.value,
                        total = total,
                        productsCount = products.count(),
                    )
                    orders = orders.plus(order)
                }

                println("Orders: $orders")
                ordersState.postValue(orders)
            }
        }
    }


    suspend fun createPayment(): StripePayment? {
        val response = api.createPayment(
            CreatePaymentRequest(
                userId = 1,
                products = cartProducts
            )
        )
        if (response.isSuccessful) {
            val body = response.body()!!
            return body.stripePayment
        }
        return null
    }

    suspend fun confirmPayment(payment: StripePayment): Boolean {
        val response = api.confirmPayment(
            ConfirmPaymentRequest(
                userId = 1,
                paymentId = payment.paymentId,
                products = cartProducts
            )
        )
        if (response.isSuccessful) {
            val body = response.body()!!
            return body.success;
        }
        return false
    }

    fun add(product: Product) {
        val item = cartProducts.find { el -> el.product.name == product.name }
        if (item == null) cartProductsState.value = cartProducts.plus(CartProduct(product))
        else item.quantity += 1
        cartProductsState.postValue(cartProducts)
    }

    fun remove(product: Product) {
        val item = cartProducts.find { e -> e.product.name == product.name }

        if (item != null) {
            if (item.quantity > 1) item.quantity -= 1
            else cartProductsState.value = cartProducts.minus(item)
        }

        cartProductsState.postValue(cartProducts)
    }


}