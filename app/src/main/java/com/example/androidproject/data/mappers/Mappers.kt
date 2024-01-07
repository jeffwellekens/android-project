package com.example.androidproject.data.mappers

import com.example.androidproject.data.local.entity.CheapestPriceEntity
import com.example.androidproject.data.local.entity.DealDetailEntity
import com.example.androidproject.data.local.entity.DealEntity
import com.example.androidproject.data.local.entity.GameInfoEntity
import com.example.androidproject.data.local.entity.DealStoreEntity
import com.example.androidproject.data.local.entity.ImagesEntity
import com.example.androidproject.data.local.entity.StoreEntity
import com.example.androidproject.data.remote.dto.CheapestPriceDto
import com.example.androidproject.data.remote.dto.DealDetailDto
import com.example.androidproject.data.remote.dto.DealDto
import com.example.androidproject.data.remote.dto.GameInfoDto
import com.example.androidproject.data.remote.dto.DealStoreDto
import com.example.androidproject.data.remote.dto.ImagesDto
import com.example.androidproject.data.remote.dto.StoreDto
import com.example.androidproject.domain.model.CheapestPrice
import com.example.androidproject.domain.model.Deal
import com.example.androidproject.domain.model.DealDetail
import com.example.androidproject.domain.model.GameInfo
import com.example.androidproject.domain.model.DealStore
import com.example.androidproject.domain.model.Images
import com.example.androidproject.domain.model.Store

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

fun DealDetailEntity.toDealDetail(): DealDetail {
    return DealDetail(
        gameInfo = gameInfo.toGameInfo(),
        cheaperDealStores = cheaperStores.map { it.toStore() },
        cheapestPrice = cheapestPrice.toCheapestPrice()
    )
}

fun GameInfoEntity.toGameInfo(): GameInfo {
    return GameInfo(
        storeID = storeID,
        gameID = gameID,
        name = name,
        steamAppID = steamAppID,
        salePrice = salePrice,
        retailPrice = retailPrice,
        steamRatingText = steamRatingText,
        steamRatingPercent = steamRatingPercent,
        metacriticScore = metacriticScore,
        releaseDate = releaseDate,
        publisher = publisher,
        thumb = thumb,
    )
}

fun DealStoreEntity.toStore(): DealStore {
    return DealStore(
        dealID = dealID,
        storeID = storeID,
        salePrice = salePrice,
        retailPrice = retailPrice,
    )
}

fun CheapestPriceEntity.toCheapestPrice(): CheapestPrice {
    return CheapestPrice(
        price = price,
        date = date
    )
}

fun DealDetailDto.toDealDetailEntity(dealId: String): DealDetailEntity {
    return DealDetailEntity(
        dealId = dealId,
        gameInfo = gameInfo.toGameInfoEntity(),
        cheaperStores = cheaperStores.map { it.toStoreEntity() },
        cheapestPrice = cheapestPrice.toCheapestPriceEntity()
    )
}

fun GameInfoDto.toGameInfoEntity(): GameInfoEntity {
    return GameInfoEntity(
        storeID = storeID,
        gameID = gameID,
        name = name,
        steamAppID = steamAppID,
        salePrice = salePrice,
        retailPrice = retailPrice,
        steamRatingText = steamRatingText,
        steamRatingPercent = steamRatingPercent,
        metacriticScore = metacriticScore,
        releaseDate = releaseDate,
        publisher = publisher,
        thumb = thumb,
    )
}

fun DealStoreDto.toStoreEntity(): DealStoreEntity {
    return DealStoreEntity(
        dealID = dealID,
        storeID = storeID,
        salePrice = salePrice,
        retailPrice = retailPrice,
    )
}

fun CheapestPriceDto.toCheapestPriceEntity(): CheapestPriceEntity {
    return CheapestPriceEntity(
        price = price ?: "N/A",
        date = date
    )
}

fun StoreEntity.toStore(): Store {
    return Store(
        storeId = storeID,
        storeName = storeName,
        isActive = isActive,
        images = images.toImages()
    )
}

fun StoreDto.toStoreEntity(): StoreEntity {
    return StoreEntity(
        storeID = storeID,
        storeName = storeName,
        isActive = isActive,
        images = images.toImagesEntity()
    )
}

fun ImagesEntity.toImages(): Images {
    return Images(
        banner = banner,
        logo = logo,
        icon = icon
    )
}

fun ImagesDto.toImagesEntity(): ImagesEntity {
    return ImagesEntity(
        banner = banner,
        logo = logo,
        icon = icon
    )
}

fun Deal.toDealEntity(): DealEntity {
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
        thumb = thumb ?: "",
        title = title
    )
}

fun DealDetail.toDealDetailEntity(dealId: String): DealDetailEntity {
    return DealDetailEntity(
        dealId = dealId,
        gameInfo = gameInfo.toGameInfoEntity(),
        cheaperStores = cheaperDealStores.map {it.toDealStoreEntity()},
        cheapestPrice = cheapestPrice.toCheapestPriceEntity()
    )
}

fun GameInfo.toGameInfoEntity(): GameInfoEntity {
    return GameInfoEntity(
        storeID = storeID,
        gameID = gameID,
        name = name,
        steamAppID = steamAppID,
        salePrice = salePrice,
        retailPrice = retailPrice,
        steamRatingText = steamRatingText,
        steamRatingPercent = steamRatingPercent,
        metacriticScore = metacriticScore,
        releaseDate = releaseDate,
        publisher = publisher,
        thumb = thumb,
    )
}

fun DealStore.toDealStoreEntity(): DealStoreEntity {
    return DealStoreEntity(
        dealID = dealID,
        storeID = storeID,
        salePrice = salePrice,
        retailPrice = retailPrice,
    )
}

fun CheapestPrice.toCheapestPriceEntity(): CheapestPriceEntity {
    return CheapestPriceEntity(
        price = price ?: "N/A",
        date = date
    )
}

fun Store.toStoreEntity(): StoreEntity {
    return StoreEntity(
        storeID = storeId,
        storeName = storeName,
        isActive = isActive,
        images = images.toImagesEntity()
    )
}

fun Images.toImagesEntity(): ImagesEntity {
    return ImagesEntity(
        banner = banner,
        logo = logo,
        icon = icon
    )
}