package com.aydar.featuredoglist.di

import com.aydar.featuredoglist.domain.ShowDogsUseCase
import com.aydar.featuredoglist.presentation.BreedsViewModel
import org.koin.dsl.module

val dogListModule = module {

    factory { ShowDogsUseCase(get()) }
    factory { BreedsViewModel(get()) }
}