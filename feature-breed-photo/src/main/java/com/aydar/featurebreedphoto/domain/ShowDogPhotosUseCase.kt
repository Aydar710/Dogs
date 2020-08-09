package com.aydar.featurebreedphoto.domain

import com.aydar.core.repository.IDogsRepository

class ShowDogPhotosUseCase(private val dogsRepository: IDogsRepository) {

    suspend fun invoke(breed : String): List<String>? {
        return dogsRepository.getDogPhotos(breed)
    }
}