package com.example.androidproject.data.local.entity

import androidx.room.Entity

@Entity
data class ImagesEntity(
    val banner: String,
    val logo: String,
    val icon: String
)
