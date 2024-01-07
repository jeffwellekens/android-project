package com.example.androidproject.domain.model

data class Deal(
    val dealID: String,
    val title: String,
    val storeID: String,
    val gameID: String,
    val salePrice: String,
    val normalPrice: String,
    val isOnSale: String,
    val dealRating: String,
    val thumb: String?,
    val steamRatingText: String?,
    val metacriticScore: String?
)
