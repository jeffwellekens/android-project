package com.example.androidproject.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DealStoreDto(
    val dealID: String,
    val storeID: String,
    val salePrice: String,
    val retailPrice: String,
)
