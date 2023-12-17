package com.poker.yks.repository

import com.poker.yks.data.registration.RegistrationRequest
import com.poker.yks.data.registration.RegistrationResponse
import com.poker.yks.rest.rankingomat.RankingomatClient
import retrofit2.Response

class RegistrationRepository {

    private val api = RankingomatClient.rankingomatService

    suspend fun postRegistrationCredentials(registrationRequest: RegistrationRequest):
            Response<RegistrationResponse> {
        return api.registerNewAccount(registrationRequest)
    }

}