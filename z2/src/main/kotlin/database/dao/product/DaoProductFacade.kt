package database.dao.product

import models.Product

interface DAOProductFacade {
    suspend fun getProduct(id: Int): Product?
    suspend fun getAllProducts(): List<Product>
    suspend fun addProduct(name: String, description: String, price: Double, categoryId: Int): Product?
    suspend fun updateProduct(id: Int, name: String, description: String, price: Double, categoryId: Int): Boolean
    suspend fun deleteProduct(id: Int): Boolean
}