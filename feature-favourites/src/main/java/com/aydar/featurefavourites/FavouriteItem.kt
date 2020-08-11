package com.aydar.featurefavourites

import com.aydar.model.LikedPhoto

data class FavouriteItem(
    val breed: String,
    val likedPhotos: List<LikedPhoto>?
)