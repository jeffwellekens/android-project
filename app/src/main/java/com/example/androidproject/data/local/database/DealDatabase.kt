package com.example.androidproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidproject.data.local.Converters
import com.example.androidproject.data.local.dao.DealDao
import com.example.androidproject.data.local.dao.DealDetailDao
import com.example.androidproject.data.local.entity.DealDetailEntity
import com.example.androidproject.data.local.entity.DealEntity

/**
 * Room Database class representing the local database for Deals.
 *
 * @property dealDao DAO for interacting with DealEntity.
 * @property dealDetailDao DAO for interacting with DealDetailEntity.
 */
@Database(
    entities = [DealEntity::class, DealDetailEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DealDatabase : RoomDatabase() {
    /**
     * Get the [DealDao] for DealEntity interactions.
     *
     * @return The [DealDao] instance.
     */
    abstract val dealDao: DealDao
    /**
     * Get the [DealDetailDao] for DealDetailEntity interactions.
     *
     * @return The [DealDetailDao] instance.
     */
    abstract val dealDetailDao: DealDetailDao
}