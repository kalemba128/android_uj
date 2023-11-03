package models

import org.jetbrains.exposed.sql.*

data class Product(val id: Int, val name: String, val description: String, val price: Double, val categoryId: Int)

object Products : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 64)
    val description = varchar("description", 1024)
    val price = double("price")
    val categoryId = reference("categoryId", Categories.id, ReferenceOption.CASCADE, ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(id)
}