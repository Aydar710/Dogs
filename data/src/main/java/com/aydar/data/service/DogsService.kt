package com.aydar.data.service

import com.aydar.model.DogPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsService {

    @GET("breeds/list/all")
    suspend fun getDogs(): Any

    @GET("breed/{breed}/images")
    suspend fun getDogPhotos(@Path("breed") breed: String): DogPhotosResponse
}