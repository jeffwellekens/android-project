package com.example.androidproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidproject.data.local.dao.StoreDao
import com.example.androidproject.data.local.entity.StoreEntity

@Database(
    entities = [StoreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StoreDatabase : RoomDatabase() {
    abstract val storeDao: StoreDao
}
