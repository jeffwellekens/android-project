package com.example.androidproject.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DealDetailDto (
    val gameInfo: GameInfoDto,
    val cheaperStores: List<DealStoreDto>,
    val cheapestPrice: CheapestPriceDto,
)