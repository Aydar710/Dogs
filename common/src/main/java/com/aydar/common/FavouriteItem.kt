package com.aydar.common

import com.aydar.model.LikedPhoto
import java.io.Serializable

data class FavouriteItem(
    val breed: String,
    val likedPhotos: List<LikedPhoto>?
) : Serializable