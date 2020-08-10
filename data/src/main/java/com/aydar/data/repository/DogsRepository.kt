package com.aydar.data.repository

import com.aydar.core.repository.IDogsRepository
import com.aydar.data.service.DogsService
import com.aydar.model.Dog
import com.google.gson.internal.LinkedTreeMap

class DogsRepository(private val dogsService: DogsService) : IDogsRepository {

    override suspend fun getDogs(): List<Dog> {
        val response = dogsService.getDogs()
        return parseDogsResponse(response)
    }

    override suspend fun getBreedPhotos(breed: String): List<String>? {
        try {
            val response = dogsService.getbreedPhotos(breed)
            return response.message
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override suspend fun getSubbreedPhotos(breed: String, subbreed: String): List<String>? {
        val response = dogsService.getSubbreedPhotos(breed, subbreed)
        return response.message
    }

    override suspend fun getSubbreeds(breed: String): List<String>? {
        val response = dogsService.getSubbreeds(breed)
        return response.message
    }

    private fun parseDogsResponse(response: Any): List<Dog> {
        response as LinkedTreeMap<*, *>
        val message = response["message"]
        message as LinkedTreeMap<String, *>
        val dogs = mutableListOf<Dog>()
        message.keys.forEach {
            val dog = Dog(it, message[it] as List<String>)
            dogs.add(dog)
        }
        return dogs
    }
}