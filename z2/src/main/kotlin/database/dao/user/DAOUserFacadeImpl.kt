package database.dao.user

import com.example.models.User
import com.example.models.Users
import database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class DAOUserFacadeImpl : DAOUserFacade {
    private fun rowToUser(row: ResultRow) = User(
        id = row[Users.id],
        login = row[Users.login],
        password = row[Users.password],
        token = row[Users.token],
        tokenProvider = row[Users.tokenProvider]
    )

    override suspend fun signIn(login: String, password: String): User? = dbQuery {
        Users
            .select { (Users.login eq login) and (Users.password eq password) }
            .map(::rowToUser)
            .singleOrNull()
    }

    override suspend fun signUp(login: String, password: String, token: String?, tokenProvider: String?): User? = dbQuery {
        val insert = Users.insert {
            it[Users.login] = login
            it[Users.password] = password
            it[Users.token] = token ?: ""
            it[Users.tokenProvider] = tokenProvider ?: ""
        }
        insert.resultedValues?.singleOrNull()?.let(::rowToUser)
    }

    override suspend fun getUserByLogin(login: String): User? = dbQuery {
        Users
            .select { (Users.login eq login) }
            .map(::rowToUser)
            .firstOrNull()
    }

    override suspend fun getAllUsers(): List<User> = dbQuery {
        Users.selectAll().map(::rowToUser)
    }
}

val daoUser: DAOUserFacade = DAOUserFacadeImpl()