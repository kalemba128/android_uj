package models

import org.jetbrains.exposed.sql.*

data class Category(val id: Int, val name: String, val description: String, val imgUrl: String)

object Categories : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 64)
    val description = varchar("description", 1024)
    val imgUrl = varchar("imgUrl", 256)
    override val primaryKey = PrimaryKey(id)
}