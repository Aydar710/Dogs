package com.aydar.core.repository

import com.aydar.model.Dog

interface IDogsRepository {

    suspend fun getDogs(): List<Dog>
    suspend fun getBreedPhotos(breed: String): List<String>?
    suspend fun getSubbreeds(breed: String): List<String>?
    suspend fun getSubbreedPhotos(breed : String, subbreed : String) : List<String>?
}