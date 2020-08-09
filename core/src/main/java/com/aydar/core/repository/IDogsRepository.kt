package com.aydar.core.repository

import com.aydar.model.Dog

interface IDogsRepository {

    suspend fun getDogs(): List<Dog>
    suspend fun getDogPhotos(breed: String): List<String>?
}