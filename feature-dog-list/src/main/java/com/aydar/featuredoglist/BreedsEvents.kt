package com.aydar.featuredoglist

import com.aydar.model.Dog

sealed class BreedsEvents {
    class NavigateToBreedPhotos(val dog: Dog) : BreedsEvents()
    class NavigateToSubbreeds(val dog: Dog) : BreedsEvents()
    object ShowProgress : BreedsEvents()
    object HideProgress : BreedsEvents()
    object ShowError : BreedsEvents()
}