package com.rapptrlabs.androidtest.data.model

import com.google.gson.annotations.SerializedName

data class ChatsResponse(
    @SerializedName("data")
    val data: List<ChatMessageModel>,
)