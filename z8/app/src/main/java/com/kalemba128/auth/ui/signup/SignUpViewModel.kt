package com.kalemba128.auth.ui.signup

import androidx.lifecycle.ViewModel
import com.kalemba128.auth.ServerApi
import com.kalemba128.auth.RetrofitHelper
import com.kalemba128.auth.model.api.SignUpRequest
import com.kalemba128.auth.model.api.SignUpResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val serverApi = RetrofitHelper.getInstance(null).create(ServerApi::class.java)
    @OptIn(DelicateCoroutinesApi::class)
    fun signUp(login: String, password: String): Response<SignUpResponse> {
        val response = runBlocking {
            GlobalScope.async {
                return@async serverApi.signUp(SignUpRequest(login, password))
            }.await()
        }
        return response
    }
}