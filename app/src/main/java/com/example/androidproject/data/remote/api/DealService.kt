package com.example.androidproject.data.remote.api

import com.example.androidproject.data.remote.dto.DealDetailDto
import com.example.androidproject.data.remote.dto.DealDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DealService {
    @GET("deals")
    suspend fun getDeals(
        @Query("title") query: String? = null,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): List<DealDto>


    @GET("deals")
    suspend fun getDealDetail(
        @Query("id") dealId: String
    ): DealDetailDto

    companion object {
        const val BASE_URL = "https://www.cheapshark.com/api/1.0/"
    }
}