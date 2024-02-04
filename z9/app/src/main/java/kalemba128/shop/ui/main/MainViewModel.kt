package kalemba128.shop.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kalemba128.shop.RetrofitHelper
import kalemba128.shop.ServerApi
import kalemba128.shop.model.Product
import androidx.lifecycle.*
import com.example.models.StripePayment
import kalemba128.shop.model.CartProduct
import kalemba128.shop.model.api.payment.CreatePaymentRequest
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var api = RetrofitHelper.getInstance(null).create(ServerApi::class.java)

    var productsState: MutableLiveData<List<Product>> = MutableLiveData()
    var cartProductsState: MutableLiveData<List<CartProduct>> = MutableLiveData()

    val products get() = productsState.value ?: listOf()
    val cartProducts get() = cartProductsState.value ?: listOf()


    fun getProducts() {
        viewModelScope.launch {
            val response = api.getProducts()
            if (response.isSuccessful) {
                val body = response.body()!!
                productsState.postValue(body.products)
            }
        }
    }


    suspend fun createStripePayment(): StripePayment? {
        val response = api.createPayment(CreatePaymentRequest(products = cartProducts))
        if (response.isSuccessful) {
            val body = response.body()!!
            return body.stripePayment
        }
        return  null
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