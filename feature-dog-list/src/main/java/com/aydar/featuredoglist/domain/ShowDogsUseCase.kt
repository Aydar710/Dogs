package com.aydar.featuredoglist.domain

import com.aydar.core.repository.IDogsRepository
import com.aydar.model.Dog

class ShowDogsUseCase(private val dogsRepository: IDogsRepository) {

    suspend fun invoke(): List<Dog> {
        return dogsRepository.getDogs()
    }
}