package com.aydar.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {

    private const val BASE_URL = "https://dog.ceo/api/"
    private lateinit var dogsService: DogsService

    fun create(): DogsService {
        synchronized(this) {
            if (!::dogsService.isInitialized)
                dogsService = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(buildClient())
                    .build()
                    .create(DogsService::class.java)
            return dogsService
        }
    }

    private fun buildClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()
}