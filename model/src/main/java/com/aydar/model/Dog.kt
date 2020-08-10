package com.aydar.model

import java.io.Serializable

data class Dog(
    val breed: String,
    val subBreeds: List<String> = emptyList()
) : Serializable