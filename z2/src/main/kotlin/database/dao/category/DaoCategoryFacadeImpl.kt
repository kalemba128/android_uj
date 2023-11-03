package database.dao.category


import database.DatabaseFactory.dbQuery
import models.Categories
import models.Category
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class DAOCategoryFacadeImpl : DAOCategoryFacade {
    private fun resultRow(row: ResultRow) = Category(
        id = row[Categories.id],
        name = row[Categories.name],
        description = row[Categories.description],
        imgUrl = row[Categories.imgUrl]
    )

    override suspend fun getAllCategories(): List<Category> = dbQuery {
        Categories.selectAll().map(::resultRow)
    }

    override suspend fun getCategory(id: Int): Category? = dbQuery {
        Categories
            .select { Categories.id eq id }
            .map(::resultRow)
            .singleOrNull()
    }

    override suspend fun addCategory(name: String, description: String, imgUrl: String): Category? = dbQuery {
        val insertStatement = Categories.insert {
            it[Categories.name] = name
            it[Categories.description] = description
            it[Categories.imgUrl] = imgUrl
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRow)
    }

    override suspend fun updateCategory(id: Int, name: String, description: String, imgUrl: String): Boolean = dbQuery {
        Categories.update({ Categories.id eq id }) {
            it[Categories.name] = name
            it[Categories.description] = description
            it[Categories.imgUrl] = imgUrl
        } > 0
    }

    override suspend fun deleteCategory(id: Int): Boolean = dbQuery {
        Categories.deleteWhere { Categories.id eq id } > 0
    }
}

val daoCategory: DAOCategoryFacade = DAOCategoryFacadeImpl()