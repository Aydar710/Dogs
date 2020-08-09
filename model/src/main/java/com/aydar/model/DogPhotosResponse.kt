package com.aydar.model

import com.google.gson.annotations.SerializedName

data class DogPhotosResponse(

    @SerializedName("message")
    var message: List<String>? = null,
    @SerializedName("status")
    var status: String? = null
)