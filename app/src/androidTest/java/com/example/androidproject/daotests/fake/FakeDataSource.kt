package com.example.androidproject.daotests.fake

import com.example.androidproject.domain.model.CheapestPrice
import com.example.androidproject.domain.model.DealDetail
import com.example.androidproject.domain.model.DealStore
import com.example.androidproject.domain.model.GameInfo
import com.example.androidproject.domain.model.Images
import com.example.androidproject.domain.model.Store

object FakeDataSource {
    val gameInfo1 = GameInfo(
        storeID = "1",
        gameID = "game1",
        name = "GameName1",
        steamAppID = "steamId1",
        salePrice = "20.00",
        retailPrice = "50.00",
        steamRatingText = "steamRating1",
        steamRatingPercent = "20.00",
        metacriticScore = "8",
        releaseDate = 923874987,
        publisher = "N/A",
        thumb = "thumb"
    )
    val cheaperDealStores1 = emptyList<DealStore>()
    val cheapestPrice1 = CheapestPrice(price = "15.00", 12039487)
    val dealDetail1 = DealDetail(gameInfo = gameInfo1, cheaperDealStores = cheaperDealStores1, cheapestPrice = cheapestPrice1)
    val store1 = Store("1", "steam", 1, Images("banner", "logo", "icon"))
    val store2 = Store("2", "Epic Games", 1, Images("banner", "logo", "icon"))
    val store3 = Store("3", "Origin", 1, Images("banner", "logo", "icon"))
}