package com.poker.yks.repository

import com.poker.yks.data.shop.ShopRequest
import com.poker.yks.rest.rankingomat.RankingomatClient

class ShopRepository {

    private val api = RankingomatClient.rankingomatService

    suspend fun postShopCredentials(shopRequest: ShopRequest) {
        return api.updateShopStatus(shopRequest)
    }

}