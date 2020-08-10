package com.aydar.featurebreedphoto.di

import com.aydar.featurebreedphoto.domain.DeletePhotoLikeUseCase
import com.aydar.featurebreedphoto.domain.SavePhotoLikeUseCase
import com.aydar.featurebreedphoto.domain.ShowDogPhotosUseCase
import com.aydar.featurebreedphoto.presentation.BreedPhotoViewModel
import org.koin.dsl.module

val breedPhotoModule = module {
    factory { ShowDogPhotosUseCase(get(), get()) }
    factory { SavePhotoLikeUseCase(get()) }
    factory { DeletePhotoLikeUseCase(get()) }
    factory { BreedPhotoViewModel(get(), get(), get()) }
}