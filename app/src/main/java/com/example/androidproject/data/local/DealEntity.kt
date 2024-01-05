package com.example.androidproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DealEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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