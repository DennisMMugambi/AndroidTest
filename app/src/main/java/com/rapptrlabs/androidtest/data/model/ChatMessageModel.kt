package com.rapptrlabs.androidtest.data.model

import com.google.gson.annotations.SerializedName

data class ChatMessageModel(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("name")
    val userName: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("message")
    val message: String
)