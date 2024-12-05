package com.rapptrlabs.androidtest.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
)
