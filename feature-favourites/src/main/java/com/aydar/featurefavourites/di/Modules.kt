package com.aydar.featurefavourites.di

import com.aydar.featurefavourites.domain.ShowAllLikedPhotosUseCase
import com.aydar.featurefavourites.presentation.FavouritesViewModel
import org.koin.dsl.module

val favouritesModule = module {
    factory { ShowAllLikedPhotosUseCase(get()) }
    factory { FavouritesViewModel(get()) }
}