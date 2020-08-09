package com.aydar.featurebreedphoto.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydar.featurebreedphoto.domain.ShowDogPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreedPhotoViewModel(private val showDogPhotosUseCase: ShowDogPhotosUseCase) : ViewModel() {

    private val _photosLiveData = MutableLiveData<List<String>>()
    val photosLiveData: LiveData<List<String>> = _photosLiveData

    fun showDogPhotos(breed: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val photos = showDogPhotosUseCase.invoke(breed)
                _photosLiveData.postValue(photos)
            }
        }
    }
}