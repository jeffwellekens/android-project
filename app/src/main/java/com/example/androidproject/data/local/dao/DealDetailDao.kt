package com.example.androidproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidproject.data.local.entity.DealDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DealDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(deal: DealDetailEntity)
    @Query("SELECT * FROM dealdetailentity AS deal WHERE deal.dealId = :dealId")
    fun getDealDetail(dealId: String): Flow<DealDetailEntity>
    @Query("SELECT EXISTS(SELECT * FROM dealdetailentity AS deal WHERE deal.dealId = :dealId)")
    fun dealDetailExists(dealId: String) : Boolean
    @Query("DELETE FROM dealdetailentity")
    suspend fun clearAll()
}