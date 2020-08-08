package com.aydar.data.service

import retrofit2.http.GET

interface DogsService {

    @GET("breeds/list/all")
    suspend fun getDogs(): Any
}