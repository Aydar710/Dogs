package com.aydar.featuredoglist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydar.common.SingleLiveEvent
import com.aydar.featuredoglist.BreedsEvents
import com.aydar.featuredoglist.domain.ShowDogsUseCase
import com.aydar.model.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreedsViewModel(private val showDogsUseCase: ShowDogsUseCase) : ViewModel() {

    private val TAG = this::class.java.simpleName
    private val _dogsLiveData = MutableLiveData<List<Dog>>()
    val dogsLiveData: LiveData<List<Dog>> = _dogsLiveData

    private val _events = SingleLiveEvent<BreedsEvents>()
    val events : LiveData<BreedsEvents> = _events

    fun showDogs() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val dogs = showDogsUseCase.invoke()
                    _dogsLiveData.postValue(dogs)
                } catch (e: Exception) {
                    Log.e(TAG, "Error when retrieving dogs", e)
                }
            }
        }
    }

    fun onBreedClicked(dog: Dog) {
        if (dog.subBreeds.isNotEmpty()) {
            _events.value = BreedsEvents.NavigateToSubbreeds(dog)
        } else {
            _events.value = BreedsEvents.NavigateToBreedPhotos(dog)
        }
    }
}