package com.aydar.featurebreedphoto.di

import com.aydar.featurebreedphoto.domain.ImageSaver
import com.aydar.featurebreedphoto.domain.ShowBreedPhotosUseCase
import com.aydar.featurebreedphoto.domain.ShowSubbreedPhotosUseCase
import com.aydar.featurebreedphoto.presentation.BreedPhotoViewModel
import org.koin.dsl.module

val breedPhotoModule = module {
    factory { ImageSaver(get()) }
    factory { ShowBreedPhotosUseCase(get(), get()) }
    factory { ShowSubbreedPhotosUseCase(get(), get()) }
    factory { BreedPhotoViewModel(get(), get(), get(), get(), get()) }
}