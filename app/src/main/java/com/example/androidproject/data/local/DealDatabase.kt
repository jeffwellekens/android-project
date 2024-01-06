package com.example.androidproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DealEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DealDatabase: RoomDatabase() {
    abstract val dao: DealDao
}