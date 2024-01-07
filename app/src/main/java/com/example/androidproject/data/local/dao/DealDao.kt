package com.example.androidproject.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.androidproject.data.local.entity.DealEntity

/**
 * Data Access Object (DAO) for dealing with DealEntity in the local Room database.
 */
@Dao
interface DealDao {
    /**
     * Upsert (Insert or Update) a list of [DealEntity] into the database.
     *
     * @param deals List of [DealEntity] objects to be upserted.
     */
    @Upsert
    suspend fun upsertAll(deals: List<DealEntity>)
    /**
     * Query to retrieve a [PagingSource] for paginating through [DealEntity] objects.
     *
     * @return A [PagingSource] for paginating through the data.
     */
    @Query("SELECT * FROM dealentity")
    fun pagingSource(): PagingSource<Int, DealEntity>
    /**
     * Query to delete all [DealEntity] entries from the database.
     */
    @Query("DELETE FROM dealentity")
    suspend fun clearAll()
}