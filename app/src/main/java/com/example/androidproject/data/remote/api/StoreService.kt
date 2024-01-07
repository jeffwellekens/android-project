package com.example.androidproject.data.remote.api

import com.example.androidproject.data.remote.dto.StoreDto
import retrofit2.http.GET

/**
 * Retrofit interface for defining API endpoints related to stores.
 */
interface StoreService {
    /**
     * Retrieve a list of stores from the API.
     *
     * @return A list of [StoreDto] objects representing stores.
     */
    @GET("stores")
    suspend fun getStores(): List<StoreDto>

    companion object {
        /**
         * Base URL for the CheapShark API.
         */
        const val BASE_URL = "https://www.cheapshark.com/api/1.0/"
    }
}