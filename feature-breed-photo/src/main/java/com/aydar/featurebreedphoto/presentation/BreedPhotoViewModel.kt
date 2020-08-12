package com.aydar.featurebreedphoto.presentation

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydar.common.SingleLiveEvent
import com.aydar.common.domain.DeletePhotoLikeUseCase
import com.aydar.common.domain.SavePhotoLikeUseCase
import com.aydar.featurebreedphoto.BreedPhotoEvents
import com.aydar.featurebreedphoto.domain.ImageSaver
import com.aydar.featurebreedphoto.domain.ShowBreedPhotosUseCase
import com.aydar.featurebreedphoto.domain.ShowSubbreedPhotosUseCase
import com.aydar.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreedPhotoViewModel(
    private val showBreedPhotosUseCase: ShowBreedPhotosUseCase,
    private val savePhotoLikeUseCase: SavePhotoLikeUseCase,
    private val deletePhotoLikeUseCase: DeletePhotoLikeUseCase,
    private val showSubbreedPhotosUseCase: ShowSubbreedPhotosUseCase,
    private val imageSaver: ImageSaver
) : ViewModel() {

    lateinit var args: BreedPhotoFragmentArgs

    private val _photosLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> = _photosLiveData

    private val _event = SingleLiveEvent<BreedPhotoEvents>()
    val event: LiveData<BreedPhotoEvents> = _event

    fun showDogPhotos(breed: String) {
        if (args.isSubbreed) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    _event.postValue(BreedPhotoEvents.ShowProgress)
                    val photos = showSubbreedPhotosUseCase.invoke(args.breedName, args.subbreed)
                    _event.postValue(BreedPhotoEvents.HideProgress)
                    _photosLiveData.postValue(photos)
                }
            }
        } else {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    _event.postValue(BreedPhotoEvents.ShowProgress)
                    val photos = showBreedPhotosUseCase.invoke(breed)
                    _event.postValue(BreedPhotoEvents.HideProgress)
                    _photosLiveData.postValue(photos)
                }
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
                _event.value = uri?.let { BreedPhotoEvents.ShareImage(it) }
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