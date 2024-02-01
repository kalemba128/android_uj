package com.kalemba128.auth.ui.signin


import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.kalemba128.auth.ServerApi
import com.kalemba128.auth.GithubApi
import com.kalemba128.auth.GithubAuthApi
import com.kalemba128.auth.GithubConfig
import com.kalemba128.auth.RetrofitHelper
import com.kalemba128.auth.model.User
import com.kalemba128.auth.model.api.SignInRequest
import kotlinx.coroutines.*

class SignInViewModel : ViewModel() {
    private val serverApi = RetrofitHelper.getInstance(null).create(ServerApi::class.java)

    private val githubAuthApi =
        RetrofitHelper.getInstance(GithubConfig.baseUrl).create(GithubAuthApi::class.java)

    private val githubApi =
        RetrofitHelper.getInstance(GithubConfig.apiUrl).create(GithubApi::class.java)

    @OptIn(DelicateCoroutinesApi::class)
    fun signIn(login: String, password: String, token: String?, tokenProvider: String?): User? {
        val response = runBlocking {
            GlobalScope.async {
                return@async serverApi.login(SignInRequest(login, password, token, tokenProvider))
            }.await()
        }

        if (response.isSuccessful) {
            val body = response.body()!!
            return User(
                id = body.user.id,
                login = body.user.login,
                password = body.user.password,
                token = body.user.token,
                tokenProvider = body.user.tokenProvider,
            )
        }
        return null
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun signInGithub(code: String): User? {
        getGithubToken(code).let {
            val response = runBlocking {
                GlobalScope.async { githubApi.getGithubUser(authHeader = "Bearer $it") }
                    .await()
            }
            if (response.isSuccessful) {
                val body = response.body()!!;
                return signIn(body.login, "", it, "GITHUB")
            }
        }
        return null
    }

    fun signInGoogle(account: GoogleSignInAccount): User? {
        return signIn(account.email!!, "", account.idToken!!, "GOOGLE")
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun getGithubToken(code: String): String? {
        val response = runBlocking {
            GlobalScope.async {
                githubAuthApi.getGithubAccessToken(
                    clientId = GithubConfig.clientId,
                    secret = GithubConfig.clientSecret,
                    code = code
                )
            }.await()
        }
        return response.body()?.accessToken
    }
}