package com.aydar.featuresubbreeds.di

import com.aydar.featuresubbreeds.presentation.SubbreedsViewModel
import org.koin.dsl.module

val subbreedsModule = module {
    factory { SubbreedsViewModel() }
}