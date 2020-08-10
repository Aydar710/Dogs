package com.aydar.data.service

import com.aydar.model.DogPhotosResponse
import com.aydar.model.SubbreedResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsService {

    @GET("breeds/list/all")
    suspend fun getDogs(): Any

    @GET("breed/{breed}/images")
    suspend fun getbreedPhotos(@Path("breed") breed: String): DogPhotosResponse

    @GET("breed/{breed}/{subbreed}/images")
    suspend fun getSubbreedPhotos(
        @Path("breed") breed: String,
        @Path("subbreed") subbreed: String
    ): DogPhotosResponse

    @GET("breed/{breed}/list")
    suspend fun getSubbreeds(@Path("breed") breed: String): SubbreedResponse
}