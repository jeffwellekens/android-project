package com.example.androidproject.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.androidproject.data.local.entity.StoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {
    @Upsert
    suspend fun upsertAll(deals: List<StoreEntity>)
    @Query("SELECT * FROM storeentity")
    fun getAllStores(): Flow<List<StoreEntity>>

    @Query("SELECT * FROM storeentity WHERE storeID = :storeId")
    fun getStoreById(storeId: String) : Flow<StoreEntity>
    @Query("DELETE FROM storeentity")
    suspend fun clearAll()
}