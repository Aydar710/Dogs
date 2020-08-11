package com.aydar.model

//Used for saving likes into firestore
data class LikedPhoto(
    val breed: String = "",
    val url: String = ""
)