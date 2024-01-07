package com.example.androidproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidproject.data.local.dao.StoreDao
import com.example.androidproject.data.local.entity.StoreEntity

/**
 * Room Database class representing the local database for Stores.
 *
 * @property storeDao DAO for interacting with StoreEntity.
 */
@Database(
    entities = [StoreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StoreDatabase : RoomDatabase() {
    /**
     * Get the [StoreDao] for StoreEntity interactions.
     *
     * @return The [StoreDao] instance.
     */
    abstract val storeDao: StoreDao
}
