package com.aydar.model

import java.io.Serializable

//Used for saving likes into firestore
data class LikedPhoto(
    val breed: String = "",
    val url: String = "",
    var isLiked: Boolean = true
) : Serializable