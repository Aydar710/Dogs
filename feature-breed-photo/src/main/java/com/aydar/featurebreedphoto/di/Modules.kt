package com.aydar.featurebreedphoto.di

import com.aydar.featurebreedphoto.domain.*
import com.aydar.featurebreedphoto.presentation.BreedPhotoViewModel
import org.koin.dsl.module

val breedPhotoModule = module {
    factory { ImageSaver(get()) }
    factory { ShowBreedPhotosUseCase(get(), get()) }
    factory { SavePhotoLikeUseCase(get()) }
    factory { DeletePhotoLikeUseCase(get()) }
    factory { ShowSubbreedPhotosUseCase(get(), get()) }
    factory { BreedPhotoViewModel(get(), get(), get(), get(), get()) }
}