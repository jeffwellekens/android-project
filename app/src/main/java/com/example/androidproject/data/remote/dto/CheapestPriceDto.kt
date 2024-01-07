package com.example.androidproject.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CheapestPriceDto(
    val price: String? = "N/A",
    val date: Int
)
