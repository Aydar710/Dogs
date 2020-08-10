package com.aydar.model

import com.google.gson.annotations.SerializedName

data class SubbreedResponse(
    @SerializedName("message")
    val message: List<String>?,
    @SerializedName("status")
    val status: String
)