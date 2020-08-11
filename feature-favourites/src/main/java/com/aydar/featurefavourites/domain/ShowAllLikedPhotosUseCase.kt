package com.aydar.featurefavourites.domain

import com.aydar.core.repository.ILikedPhotosRepository
import com.aydar.model.LikedPhoto

class ShowAllLikedPhotosUseCase(private val likedPhotosRepository: ILikedPhotosRepository) {

    suspend fun invoke(): List<LikedPhoto> {
        return likedPhotosRepository.getAllLikedPhotos()
    }
}