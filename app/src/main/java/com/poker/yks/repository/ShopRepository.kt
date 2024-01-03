package com.poker.yks.repository

import com.poker.yks.data.shop.TokensRequest
import com.poker.yks.data.shop.TokensResponse
import com.poker.yks.data.shop.VipRequest
import com.poker.yks.data.shop.VipResponse
import com.poker.yks.rest.rankingomat.RankingomatClient
import retrofit2.Response

class ShopRepository {

    private val api = RankingomatClient.rankingomatService

    suspend fun postVipCredentials(vipRequest: VipRequest): Response<VipResponse> {
        return api.updateVipStatus(vipRequest)
    }

    suspend fun postTokensCredentials(tokensRequest: TokensRequest): Response<TokensResponse> {
        return api.updateTokensNumber(tokensRequest)
    }

}