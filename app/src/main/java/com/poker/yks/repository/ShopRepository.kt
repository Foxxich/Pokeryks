package com.poker.yks.repository

import com.poker.yks.data.shop.TokensRequest
import com.poker.yks.data.shop.VipRequest
import com.poker.yks.rest.rankingomat.RankingomatClient

class ShopRepository {

    private val api = RankingomatClient.rankingomatService

    suspend fun postVipCredentials(vipRequest: VipRequest) {
        return api.updateVipStatus(vipRequest)
    }

    suspend fun postTokensCredentials(tokensRequest: TokensRequest) {
        return api.updateTokensNumber(tokensRequest)
    }

}