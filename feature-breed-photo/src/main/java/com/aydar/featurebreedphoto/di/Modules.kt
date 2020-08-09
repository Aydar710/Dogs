package com.aydar.featurebreedphoto.di

import com.aydar.featurebreedphoto.domain.ShowDogPhotosUseCase
import com.aydar.featurebreedphoto.presentation.BreedPhotoViewModel
import org.koin.dsl.module

val breedPhotoModule = module {
    factory { ShowDogPhotosUseCase(get()) }
    factory { BreedPhotoViewModel(get()) }
}