package com.example.androidproject.data.local

import androidx.room.TypeConverter
import com.example.androidproject.data.local.entity.CheapestPriceEntity
import com.example.androidproject.data.local.entity.GameInfoEntity
import com.example.androidproject.data.local.entity.DealStoreEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()
    @TypeConverter
    fun gameInfoEntityToString(gameInfoEntity: GameInfoEntity): String {
        return gson.toJson(gameInfoEntity)
    }

    @TypeConverter
    fun stringToGameInfoEntity(gameInfoEntityString: String): GameInfoEntity {
        val objectType = object : TypeToken<GameInfoEntity>() {}.type
        return gson.fromJson(gameInfoEntityString, objectType)
    }

    @TypeConverter
    fun storeEntityToString(dealStoreEntity: List<DealStoreEntity>): String {
        return gson.toJson(dealStoreEntity)
    }

    @TypeConverter
    fun stringToStoreEntity(storeEntityString: String): List<DealStoreEntity> {
        val objectType = object : TypeToken<List<DealStoreEntity>>() {}.type
        return gson.fromJson(storeEntityString, objectType)
    }

    @TypeConverter
    fun cheapestPriceEntityToString(cheapestPriceEntity: CheapestPriceEntity): String {
        return gson.toJson(cheapestPriceEntity)
    }

    @TypeConverter
    fun stringToCheapestPriceEntity(cheapestPriceEntity: String): CheapestPriceEntity {
        val objectType = object : TypeToken<CheapestPriceEntity>() {}.type
        return gson.fromJson(cheapestPriceEntity, objectType)
    }
}