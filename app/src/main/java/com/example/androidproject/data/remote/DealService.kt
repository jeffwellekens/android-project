package com.example.androidproject.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface DealService {
    @GET("deals")
    suspend fun getDeals(
        @Query("title") query: String? = null,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): List<DealDto>

    companion object {
        const val BASE_URL = "https://www.cheapshark.com/api/1.0/"
    }
}