package com.example.androidproject.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class StoreDto(
    val storeID: String,
    val storeName: String,
    val isActive: Int,
    val images: ImagesDto
)
