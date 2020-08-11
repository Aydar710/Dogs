package com.aydar.featurefavourites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydar.featurefavourites.FavouriteItem
import com.aydar.featurefavourites.domain.ShowAllLikedPhotosUseCase
import kotlinx.coroutines.launch

class FavouritesViewModel(private val showAllLikedPhotosUseCase: ShowAllLikedPhotosUseCase) :
    ViewModel() {

    private val _favouriteItems = MutableLiveData<List<FavouriteItem>>()
    val favouriteItems: LiveData<List<FavouriteItem>> = _favouriteItems

    fun showBreeds() {
        viewModelScope.launch {
            val likedPhotos = showAllLikedPhotosUseCase.invoke()
            val photosMap = likedPhotos.groupBy {
                it.breed
            }
            val favouriteItems = mutableListOf<FavouriteItem>()
            photosMap.keys.forEach {
                favouriteItems.add(FavouriteItem(it, photosMap[it]))
            }
            _favouriteItems.postValue(favouriteItems)
        }
    }
}