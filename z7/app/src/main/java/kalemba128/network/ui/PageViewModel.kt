package kalemba128.network.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kalemba128.network.AppDatabase
import kalemba128.network.RetrofitHelper
import kalemba128.network.api.Api
import kalemba128.network.models.Category
import kalemba128.network.models.Product
import kotlinx.coroutines.*

class PageViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getInstance(application)

    val api: Api = RetrofitHelper.getInstance().create(Api::class.java)

    var products: MutableLiveData<List<Product>> = MutableLiveData(listOf())
    var categories: MutableLiveData<List<Category>> = MutableLiveData(listOf())

    @OptIn(DelicateCoroutinesApi::class)
    fun getProducts() {
        GlobalScope.launch {
            val response = api.getProducts()
            if (response.isSuccessful) {
                val newProducts = response.body()!!.products
                products.postValue(newProducts)
                insertDatabaseProducts(newProducts)
            } else {
                products.postValue(getDatabaseProducts())
            }
        }
    }

    private fun getDatabaseProducts(): List<Product> {
        val data = runBlocking {
            CoroutineScope(Dispatchers.IO).async { database!!.productDao().getAll() }.await()
        }
        return data
    }

    private fun insertDatabaseProducts(products: List<Product>) {
        CoroutineScope(Dispatchers.IO).launch { database!!.productDao().insertAll(products) }
    }


    private fun insertDatabaseCategories(categories: List<Category>) {
        CoroutineScope(Dispatchers.IO).launch {
            database!!.categoryDao().insertAll(categories)
        }
    }
    private fun getDatabaseCategories(): List<Category> {
        val data = runBlocking {
            CoroutineScope(Dispatchers.IO).async { database!!.categoryDao().getAll() }.await()
        }

        return data
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getCategories() {
        GlobalScope.launch {
            val response = api.getCategories()
            if (response.isSuccessful) {
                val remoteCategories = response.body()!!.categories
                categories.postValue(remoteCategories)
                insertDatabaseCategories(remoteCategories)
            } else {
                categories.postValue(getDatabaseCategories())
            }
        }
    }
}