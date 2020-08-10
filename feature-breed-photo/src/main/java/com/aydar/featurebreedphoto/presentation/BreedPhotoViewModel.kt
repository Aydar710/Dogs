package com.aydar.featurebreedphoto.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydar.featurebreedphoto.domain.DeletePhotoLikeUseCase
import com.aydar.featurebreedphoto.domain.SavePhotoLikeUseCase
import com.aydar.featurebreedphoto.domain.ShowDogPhotosUseCase
import com.aydar.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreedPhotoViewModel(
    private val showDogPhotosUseCase: ShowDogPhotosUseCase,
    private val savePhotoLikeUseCase: SavePhotoLikeUseCase,
    private val deletePhotoLikeUseCase: DeletePhotoLikeUseCase
) : ViewModel() {

    lateinit var args: BreedPhotoFragmentArgs

    private val _photosLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> = _photosLiveData

    fun showDogPhotos(breed: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val photos = showDogPhotosUseCase.invoke(breed)
                _photosLiveData.postValue(photos)
            }
        }
    }

    fun handlePhotoLike(photo: Photo) {
        if (photo.isLiked) {
            saveLike(photo.url)
        } else {
            deleteLike(photo.url)
        }
    }

    private fun saveLike(photoUrl: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                savePhotoLikeUseCase.invoke(args.breedName, photoUrl)
            }
        }
    }

    private fun deleteLike(photoUrl: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deletePhotoLikeUseCase.invoke(args.breedName, photoUrl)
            }
        }
    }
}