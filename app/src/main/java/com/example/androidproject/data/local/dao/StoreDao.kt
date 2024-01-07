package com.example.androidproject.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.androidproject.data.local.entity.StoreEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for dealing with StoreEntity in the local Room database.
 */
@Dao
interface StoreDao {
    /**
     * Upsert (Insert or Update) a list of [StoreEntity] into the database.
     *
     * @param deals List of [StoreEntity] objects to be upserted.
     */
    @Upsert
    suspend fun upsertAll(deals: List<StoreEntity>)
    /**
     * Query to retrieve all [StoreEntity]s as a Flow.
     *
     * @return A [Flow] emitting a list of all [StoreEntity] objects in the database.
     */
    @Query("SELECT * FROM storeentity")
    fun getAllStores(): Flow<List<StoreEntity>>
    /**
     * Query to retrieve a [StoreEntity] by its unique store ID as a Flow.
     *
     * @param storeId The unique identifier of the store.
     * @return A [Flow] emitting the [StoreEntity] with the specified store ID.
     */
    @Query("SELECT * FROM storeentity WHERE storeID = :storeId")
    fun getStoreById(storeId: String) : Flow<StoreEntity>
    /**
     * Query to delete all [StoreEntity] entries from the database.
     */
    @Query("DELETE FROM storeentity")
    suspend fun clearAll()
}