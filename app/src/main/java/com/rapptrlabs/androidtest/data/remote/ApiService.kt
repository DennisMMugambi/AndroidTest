package com.rapptrlabs.androidtest.data.remote

import com.rapptrlabs.androidtest.data.model.ChatsResponse
import com.rapptrlabs.androidtest.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("Tests/scripts/login.php")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<LoginResponse>

    @GET("Tests/scripts/chat_log.php")
    suspend fun fetchChats() : ChatsResponse
}