package com.example.androidproject.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.androidproject.data.local.entity.DealEntity

@Dao
interface DealDao {
    @Upsert
    suspend fun upsertAll(deals: List<DealEntity>)
    @Query("SELECT * FROM dealentity")
    fun pagingSource(): PagingSource<Int, DealEntity>
    @Query("DELETE FROM dealentity")
    suspend fun clearAll()
}