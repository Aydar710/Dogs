package com.aydar.featurefavouriephotos.di

import com.aydar.featurefavouriephotos.presentation.FavouritePhotosViewModel
import org.koin.dsl.module

val favouritePhotosModule = module {
    factory { FavouritePhotosViewModel(get(), get(), get()) }
}