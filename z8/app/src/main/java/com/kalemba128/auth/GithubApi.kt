package com.kalemba128.auth


import com.kalemba128.auth.model.AccessToken
import com.kalemba128.auth.model.api.GithubUser
import retrofit2.Response
import retrofit2.http.*

interface GithubAuthApi {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getGithubAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") secret: String,
        @Field("code") code: String,
    ): Response<AccessToken>
}

interface GithubApi {
    @Headers("Accept: application/json")
    @GET("user")
    suspend fun getGithubUser(
        @Header("Authorization") authHeader: String
    ): Response<GithubUser>
}