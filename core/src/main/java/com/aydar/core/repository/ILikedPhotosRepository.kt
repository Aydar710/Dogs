package com.aydar.core.repository

import com.aydar.model.LikedPhoto

interface ILikedPhotosRepository {
    suspend fun savePhotoLike(breed: String, photoUrl: String)
    suspend fun deletePhotoLike(breed: String, photoUrl: String)
    suspend fun getLikedPhotosByBreed(breed: String): List<LikedPhoto>
    suspend fun getAllLikedPhotos(): List<LikedPhoto>
}