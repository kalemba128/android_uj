package database.dao.user

import com.example.models.User
interface DAOUserFacade {
    suspend fun signIn(login: String, password: String): User?
    suspend fun signUp(login: String, password: String, token: String?, tokenProvider: String?): User?
    suspend fun getUserByLogin(login: String): User?
    suspend fun getAllUsers(): List<User>
}