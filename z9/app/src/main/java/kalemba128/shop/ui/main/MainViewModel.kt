package kalemba128.shop.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kalemba128.shop.RetrofitHelper
import kalemba128.shop.ServerApi
import kalemba128.shop.model.Product
import kalemba128.shop.ui.cart.CartProduct
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var api = RetrofitHelper.getInstance(null).create(ServerApi::class.java)

    var products: MutableLiveData<List<Product>> = MutableLiveData()

    var cart: MutableLiveData<List<CartProduct>> = MutableLiveData()
    private var cartProducts = listOf<CartProduct>()

    fun getProducts() {
        viewModelScope.launch {
            val response = api.getProducts()
            if (response.isSuccessful) {
                val body = response.body()!!
                products.postValue(body.products)
            }
        }
    }

    fun add(product: Product) {
        val item = cartProducts.find { el -> el.product.name == product.name }
        if (item == null) cartProducts = cartProducts.plus(CartProduct(product))
        else item.count += 1
        cart.postValue(cartProducts)
    }

    fun remove(product: Product) {
        val item = cartProducts.find { e -> e.product.name == product.name }

        if (item != null) {
            if (item.count > 1) item.count -= 1
            else cartProducts = cartProducts.minus(item)
        }

        cart.postValue(cartProducts)
    }

}