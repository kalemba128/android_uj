package com.example.models

import org.jetbrains.exposed.sql.*

data class User(
    val id: Int,
    val login: String,
    val password: String,
    val token: String = "",
    val tokenProvider: String = ""
)

object Users : Table() {
    val id = integer("id").autoIncrement()
    val login = varchar("login", 256)
    val password = varchar("password", 256).default("")
    val token = varchar("token", 2048).default("")
    val tokenProvider = varchar("tokenProvider", 256).default("")
    override val primaryKey = PrimaryKey(id)
}