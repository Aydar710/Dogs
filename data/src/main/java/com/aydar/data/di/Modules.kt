package com.aydar.data.di

import com.aydar.core.repository.IDogsRepository
import com.aydar.core.repository.ILikedPhotosRepository
import com.aydar.data.repository.DogsRepository
import com.aydar.data.repository.LikedPhotosRepository
import com.aydar.data.service.ServiceFactory
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val dataModule = module {
    single { ServiceFactory.create() }
    single { Firebase.firestore }
    factory<IDogsRepository> { DogsRepository(get()) }
    factory<ILikedPhotosRepository> { LikedPhotosRepository(get()) }
}