package com.aydar.featurebreedphoto.presentation

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydar.common.SingleLiveEvent
import com.aydar.featurebreedphoto.BreedPhotoCommands
import com.aydar.featurebreedphoto.domain.DeletePhotoLikeUseCase
import com.aydar.featurebreedphoto.domain.ImageSaver
import com.aydar.featurebreedphoto.domain.SavePhotoLikeUseCase
import com.aydar.featurebreedphoto.domain.ShowDogPhotosUseCase
import com.aydar.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreedPhotoViewModel(
    private val showDogPhotosUseCase: ShowDogPhotosUseCase,
    private val savePhotoLikeUseCase: SavePhotoLikeUseCase,
    private val deletePhotoLikeUseCase: DeletePhotoLikeUseCase,
    private val imageSaver: ImageSaver
) : ViewModel() {

    lateinit var args: BreedPhotoFragmentArgs

    private val _photosLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> = _photosLiveData

    private val _event = SingleLiveEvent<BreedPhotoCommands>()
    val event: LiveData<BreedPhotoCommands> = _event

    fun showDogPhotos(breed: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _event.postValue(BreedPhotoCommands.ShowProgress)
                val photos = showDogPhotosUseCase.invoke(breed)
                _event.postValue(BreedPhotoCommands.HideProgress)
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

    fun onShareActionClicked(image: ImageView) {
        viewModelScope.launch {
            val uri = imageSaver.saveImage(image)

            withContext(Dispatchers.Main) {
                _event.value = uri?.let { BreedPhotoCommands.ShareImage(it) }
            }
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