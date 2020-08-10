package com.aydar.featurebreedphoto.domain

import com.aydar.core.repository.ILikedPhotosRepository

class DeletePhotoLikeUseCase(private val likedPhotosRepository: ILikedPhotosRepository) {

    suspend fun invoke(breed : String, photoUrl : String){
        likedPhotosRepository.deletePhotoLike(breed, photoUrl)
    }
}