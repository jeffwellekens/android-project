package com.example.androidproject.domain.model

data class Store(
    val storeId: String,
    val storeName: String,
    val isActive: Int,
    val images: Images
)
