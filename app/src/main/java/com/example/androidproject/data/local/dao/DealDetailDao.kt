package com.example.androidproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidproject.data.local.entity.DealDetailEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for dealing with DealDetailEntity in the local Room database.
 */
@Dao
interface DealDetailDao {
    /**
     * Insert or replace a single [DealDetailEntity] into the database.
     *
     * @param deal The [DealDetailEntity] object to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(deal: DealDetailEntity)
    /**
     * Query to retrieve the [DealDetailEntity] for a specific deal ID as a Flow.
     *
     * @param dealId The unique identifier of the deal.
     * @return A [Flow] emitting the [DealDetailEntity] for the specified deal ID.
     */
    @Query("SELECT * FROM dealdetailentity AS deal WHERE deal.dealId = :dealId")
    fun getDealDetail(dealId: String): Flow<DealDetailEntity>
    /**
     * Query to check if a [DealDetailEntity] with the specified deal ID exists in the database.
     *
     * @param dealId The unique identifier of the deal.
     * @return `true` if a [DealDetailEntity] with the specified deal ID exists; otherwise, `false`.
     */
    @Query("SELECT EXISTS(SELECT * FROM dealdetailentity AS deal WHERE deal.dealId = :dealId)")
    fun dealDetailExists(dealId: String) : Boolean
    /**
     * Query to delete all [DealDetailEntity] entries from the database.
     */
    @Query("DELETE FROM dealdetailentity")
    suspend fun clearAll()
}