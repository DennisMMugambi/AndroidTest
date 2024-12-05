package com.rapptrlabs.androidtest.data.repository

import com.rapptrlabs.androidtest.data.model.ChatMessageModel
import com.rapptrlabs.androidtest.data.model.ChatsResponse
import com.rapptrlabs.androidtest.data.model.LoginResponse
import com.rapptrlabs.androidtest.data.remote.ApiService
import timber.log.Timber
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun login(username : String, password: String) : Triple<String, String, String> {

        var code = ""
        var message = ""
        var time = ""

        try {
            val response = apiService.login(username, password)
            code = response.body()?.code ?: "Error"
            message = response.body()?.message ?: "401 Unauthorized"
            time = response.raw().receivedResponseAtMillis.toString()

            Timber.i("server call at repository ${code} message ${message}")
        } catch (e: Exception) {
            message = e.message.toString()
        }


        return Triple(code, message, time)
    }

    suspend fun getChats() : ChatsResponse {
        return try {
            apiService.fetchChats()
        } catch (e: Exception) {
            ChatsResponse(emptyList())
        }
    }
}