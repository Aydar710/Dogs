package com.aydar.featurefavourites

sealed class FavouriteEvents {
    object ShowProgress : FavouriteEvents()
    object HideProgress : FavouriteEvents()
}