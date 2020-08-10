package com.aydar.featuresubbreeds

sealed class SubbreedsEvents {
    class NavigateToBreedPhotos(val subbreed : String) : SubbreedsEvents()
}