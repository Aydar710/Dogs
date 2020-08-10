package com.aydar.featurebreedphoto.domain

import com.aydar.core.repository.IDogsRepository
import com.aydar.core.repository.ILikedPhotosRepository
import com.aydar.model.Photo

class ShowBreedPhotosUseCase(
    private val dogsRepository: IDogsRepository,
    private val photosRepository: ILikedPhotosRepository
) {

    suspend fun invoke(breed: String): List<Photo> {
        val photoUrls = dogsRepository.getBreedPhotos(breed)
        val likedPhotos = photosRepository.getLikedPhotos(breed)
        val likedPhotoUrls = likedPhotos.map {
            it.url
        }
        val photos = mutableListOf<Photo>()

        photoUrls?.forEach { url ->
            photos.add(Photo(url = url, isLiked = likedPhotoUrls.contains(url)))
        }

        return photos
    }
}