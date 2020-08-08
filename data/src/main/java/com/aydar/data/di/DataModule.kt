package com.aydar.data.di

import com.aydar.core.repository.IDogsRepository
import com.aydar.data.repository.DogsRepository
import com.aydar.data.service.ServiceFactory
import org.koin.dsl.module

val dataModule = module {
    single { ServiceFactory.create() }
    factory<IDogsRepository> { DogsRepository(get()) }
}