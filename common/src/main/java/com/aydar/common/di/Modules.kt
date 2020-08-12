package com.aydar.common.di

import com.aydar.common.domain.DeletePhotoLikeUseCase
import com.aydar.common.domain.SavePhotoLikeUseCase
import org.koin.dsl.module

val commonModule = module{
    factory { SavePhotoLikeUseCase(get()) }
    factory { DeletePhotoLikeUseCase(get()) }
}