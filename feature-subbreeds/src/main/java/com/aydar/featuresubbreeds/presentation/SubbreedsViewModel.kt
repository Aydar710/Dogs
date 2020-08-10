package com.aydar.featuresubbreeds.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aydar.common.SingleLiveEvent
import com.aydar.featuresubbreeds.SubbreedsEvents

class SubbreedsViewModel : ViewModel() {

    var args: SubbreedsFragmentArgs? = null

    private val _subbreeds = MutableLiveData<List<String>>()
    val subbreeds: LiveData<List<String>> = _subbreeds

    private val _events = SingleLiveEvent<SubbreedsEvents>()
    val events: LiveData<SubbreedsEvents> = _events

    fun showSubbreeds() {
        args?.let {
            _subbreeds.value = it.dog.subBreeds
        }
    }

    fun onSubbreedClicked(breed: String) {
        _events.value = SubbreedsEvents.NavigateToBreedPhotos(breed)
    }
}