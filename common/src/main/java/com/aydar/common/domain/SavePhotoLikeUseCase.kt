package com.aydar.common.domain

import com.aydar.core.repository.ILikedPhotosRepository

class SavePhotoLikeUseCase(private val photosRepository: ILikedPhotosRepository) {

    suspend fun invoke(breed : String, photoUrl : String){
        photosRepository.savePhotoLike(breed, photoUrl)
    }
}