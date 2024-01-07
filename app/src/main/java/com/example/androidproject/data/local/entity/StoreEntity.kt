package com.example.androidproject.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidproject.data.remote.dto.ImagesDto

@Entity
data class StoreEntity (
    @PrimaryKey
    val storeID: String,
    val storeName: String,
    val isActive: Int,
    @Embedded
    val images: ImagesEntity
)