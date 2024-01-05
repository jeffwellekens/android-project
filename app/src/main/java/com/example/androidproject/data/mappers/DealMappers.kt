package com.example.androidproject.data.mappers

import com.example.androidproject.data.local.DealEntity
import com.example.androidproject.data.remote.DealDto
import com.example.androidproject.domain.Deal

fun DealDto.toDealEntity(): DealEntity {
    return DealEntity(
        dealID = dealID,
        dealRating = dealRating,
        gameID = gameID,
        isOnSale = isOnSale,
        metacriticScore = metacriticScore,
        steamRatingText = steamRatingText,
        normalPrice = normalPrice,
        salePrice = salePrice,
        storeID = storeID,
        thumb = thumb,
        title = title
    )
}

fun DealEntity.toDeal(): Deal {
    return Deal(
        dealID = dealID,
        dealRating = dealRating,
        gameID = gameID,
        isOnSale = isOnSale,
        metacriticScore = metacriticScore,
        steamRatingText = steamRatingText,
        normalPrice = normalPrice,
        salePrice = salePrice,
        storeID = storeID,
        thumb = thumb,
        title = title
    )
}