package com.kalemba128.auth

import com.kalemba128.auth.model.api.SignInRequest
import com.kalemba128.auth.model.api.SignInResponse
import com.kalemba128.auth.model.api.SignUpRequest
import com.kalemba128.auth.model.api.SignUpResponse
import retrofit2.Response
import retrofit2.http.*

interface ServerApi {
    @HTTP(method = "POST", path = "/signIn", hasBody = true)
    suspend fun login(@Body signInRequest: SignInRequest): Response<SignInResponse>

    @HTTP(method = "POST", path = "/signUp", hasBody = true)
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>
}