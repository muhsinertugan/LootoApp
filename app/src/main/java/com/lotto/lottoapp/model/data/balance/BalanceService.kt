package com.lotto.lottoapp.model.data.balance

import com.lotto.lottoapp.di.ENDPOINTS
import com.lotto.lottoapp.model.request.BalanceRequest
import com.lotto.lottoapp.model.response.balance.BalanceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BalanceService {
    @POST(ENDPOINTS.BALANCE_ADD_URL)
    suspend fun addBalance(
        @Body balanceAmount: BalanceRequest,
        @Header("Authorization") token: String
    ): Response<BalanceResponse>

    @GET(ENDPOINTS.BALANCE_URL)
    suspend fun getBalance(@Header("Authorization") token: String): Response<BalanceResponse>

}