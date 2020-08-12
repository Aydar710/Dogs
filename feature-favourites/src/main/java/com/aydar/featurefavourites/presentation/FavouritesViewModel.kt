package com.aydar.featurefavourites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydar.common.FavouriteItem
import com.aydar.common.SingleLiveEvent
import com.aydar.featurefavourites.FavouriteEvents
import com.aydar.featurefavourites.domain.ShowAllLikedPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesViewModel(private val showAllLikedPhotosUseCase: ShowAllLikedPhotosUseCase) :
    ViewModel() {

    private val _favouriteItems = MutableLiveData<List<FavouriteItem>>()
    val favouriteItems: LiveData<List<FavouriteItem>> = _favouriteItems

    private val _event = SingleLiveEvent<FavouriteEvents>()
    val event: LiveData<FavouriteEvents> = _event

    fun showBreeds() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _event.postValue(FavouriteEvents.ShowProgress)
                val likedPhotos = showAllLikedPhotosUseCase.invoke()
                val photosMap = likedPhotos.groupBy {
                    it.breed
                }
                val favouriteItems = mutableListOf<FavouriteItem>()
                photosMap.keys.forEach {
                    favouriteItems.add(
                        FavouriteItem(
                            it,
                            photosMap[it]
                        )
                    )
                }
                _favouriteItems.postValue(favouriteItems)
                _event.postValue(FavouriteEvents.HideProgress)
            }
        }
    }
}