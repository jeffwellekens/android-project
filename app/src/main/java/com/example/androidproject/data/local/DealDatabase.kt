package com.example.androidproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DealEntity::class],
    version = 1
)
abstract class DealDatabase: RoomDatabase() {
    abstract val dao: DealDao
}