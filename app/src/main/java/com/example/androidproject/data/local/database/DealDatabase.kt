package com.example.androidproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidproject.data.local.Converters
import com.example.androidproject.data.local.dao.DealDao
import com.example.androidproject.data.local.dao.DealDetailDao
import com.example.androidproject.data.local.entity.DealDetailEntity
import com.example.androidproject.data.local.entity.DealEntity

@Database(
    entities = [DealEntity::class, DealDetailEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DealDatabase : RoomDatabase() {
    abstract val dealDao: DealDao
    abstract val dealDetailDao: DealDetailDao
}