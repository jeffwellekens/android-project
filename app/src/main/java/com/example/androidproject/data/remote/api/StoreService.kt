package com.example.androidproject.data.remote.api

import com.example.androidproject.data.remote.dto.StoreDto
import retrofit2.http.GET

interface StoreService {
    @GET("stores")
    suspend fun getStores(): List<StoreDto>

    companion object {
        const val BASE_URL = "https://www.cheapshark.com/api/1.0/"
    }
}