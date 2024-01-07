package com.example.androidproject.domain.model

data class GameInfo (
    val storeID: String,
    val gameID: String,
    val name: String,
    val steamAppID: String?,
    val salePrice: String,
    val retailPrice: String,
    val steamRatingText: String?,
    val steamRatingPercent: String?,
    val metacriticScore: String,
    val releaseDate: Int,
    val publisher: String,
    val thumb: String,
)
