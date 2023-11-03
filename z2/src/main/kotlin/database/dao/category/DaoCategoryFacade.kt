package database.dao.category

import models.Category

interface DAOCategoryFacade {
    suspend fun getCategory(id: Int): Category?
    suspend fun getAllCategories(): List<Category>
    suspend fun addCategory(name: String, description: String, imgUrl: String): Category?
    suspend fun updateCategory(id: Int, name: String, description: String, imgUrl: String): Boolean
    suspend fun deleteCategory(id: Int): Boolean
}