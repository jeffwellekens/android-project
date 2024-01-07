package com.example.androidproject.data.remote.api

import com.example.androidproject.data.remote.dto.DealDetailDto
import com.example.androidproject.data.remote.dto.DealDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface for defining API endpoints related to deals.
 */
interface DealService {

    /**
     * Retrieve a list of deals from the API.
     *
     * @param query Optional search query for filtering deals by title.
     * @param pageNumber The page number of the result set to retrieve.
     * @param pageSize The size of the result set to retrieve per page.
     * @return A list of [DealDto] objects representing deals.
     */
    @GET("deals")
    suspend fun getDeals(
        @Query("title") query: String? = null,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): List<DealDto>

    /**
     * Retrieve detailed information about a specific deal from the API.
     *
     * @param dealId The unique identifier of the deal.
     * @return A [DealDetailDto] object representing detailed information about the deal.
     */
    @GET("deals")
    suspend fun getDealDetail(
        @Query("id") dealId: String
    ): DealDetailDto

    companion object {
        /**
         * Base URL for the CheapShark API.
         */
        const val BASE_URL = "https://www.cheapshark.com/api/1.0/"
    }
}