package com.example.androidproject.domain.model

data class DealDetail (
    val gameInfo: GameInfo,
    val cheaperDealStores: List<DealStore>,
    val cheapestPrice: CheapestPrice,
)