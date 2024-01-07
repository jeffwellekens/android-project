package com.example.androidproject.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DealDto (
    val dealID: String,
    val title: String,
    val storeID: String,
    val gameID: String,
    val salePrice: String,
    val normalPrice: String,
    val isOnSale: String,
    val dealRating: String,
    val thumb: String,
    val steamRatingText: String? = null,
    val metacriticScore: String? = null
)