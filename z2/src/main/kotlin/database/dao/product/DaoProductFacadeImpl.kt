package database.dao.product


import database.DatabaseFactory.dbQuery
import models.Products
import models.Product
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class DAOProductFacadeImpl : DAOProductFacade {
    private fun resultRow(row: ResultRow) = Product(
        id = row[Products.id],
        name = row[Products.name],
        description = row[Products.description],
        price = row[Products.price],
        categoryId = row[Products.categoryId]
    )

    override suspend fun getAllProducts(): List<Product> = dbQuery {
        Products.selectAll().map(::resultRow)
    }

    override suspend fun getProduct(id: Int): Product? = dbQuery {
        Products
            .select { Products.id eq id }
            .map(::resultRow)
            .singleOrNull()
    }

    override suspend fun addProduct(name: String, description: String, price: Double, categoryId: Int): Product? =
        dbQuery {
        val insertStatement = Products.insert {
            it[Products.name] = name
            it[Products.description] = description
            it[Products.price] = price
            it[Products.categoryId] = categoryId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRow)
    }

    override suspend fun updateProduct(id: Int, name: String, description: String, price: Double, categoryId: Int):
            Boolean = dbQuery {
        Products.update({ Products.id eq id }) {
            it[Products.name] = name
            it[Products.description] = description
            it[Products.price] = price
            it[Products.categoryId] = categoryId
        } > 0
    }

    override suspend fun deleteProduct(id: Int): Boolean = dbQuery {
        Products.deleteWhere { Products.id eq id } > 0
    }
}

val daoProduct: DAOProductFacade = DAOProductFacadeImpl()