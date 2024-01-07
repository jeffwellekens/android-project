package com.example.androidproject.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImagesDto(
    val banner: String,
    val logo: String,
    val icon: String
)
