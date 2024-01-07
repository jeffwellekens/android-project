package com.example.androidproject.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DealDetailEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dealId: String,
    val gameInfo: GameInfoEntity,
    val cheaperStores: List<DealStoreEntity>,
    val cheapestPrice: CheapestPriceEntity
)