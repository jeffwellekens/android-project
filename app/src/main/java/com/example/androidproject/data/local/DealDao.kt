package com.example.androidproject.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface DealDao {
    @Upsert
    suspend fun upsertAll(deals: List<DealEntity>)
    @Query("SELECT * FROM dealentity")
    fun pagingSource(): PagingSource<Int, DealEntity>
    @Query("DELETE FROM dealentity")
    suspend fun clearAll()
}