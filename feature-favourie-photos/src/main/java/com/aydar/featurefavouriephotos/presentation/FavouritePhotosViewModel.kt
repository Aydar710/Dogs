package com.aydar.featurefavouriephotos.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydar.common.domain.DeletePhotoLikeUseCase
import com.aydar.common.domain.SavePhotoLikeUseCase
import com.aydar.model.LikedPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritePhotosViewModel(
    private val savePhotoLikeUseCase: SavePhotoLikeUseCase,
    private val deletePhotoLikeUseCase: DeletePhotoLikeUseCase
) : ViewModel() {

    lateinit var args: FavouritePhotosFragmentArgs
    private val _photos = MutableLiveData<List<LikedPhoto>>()
    val photos: LiveData<List<LikedPhoto>> = _photos

    fun showPhotos() {
        _photos.value = args.favouriteItem.likedPhotos
    }

    fun handleLike(photo: LikedPhoto) {
        if (photo.isLiked) {
            saveLike(photo.url)
        } else {
            deleteLike(photo.url)
        }
    }

    private fun saveLike(photoUrl: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                savePhotoLikeUseCase.invoke(args.favouriteItem.breed, photoUrl)
            }
        }
    }

    private fun deleteLike(photoUrl: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deletePhotoLikeUseCase.invoke(args.favouriteItem.breed, photoUrl)
            }
        }
    }
}