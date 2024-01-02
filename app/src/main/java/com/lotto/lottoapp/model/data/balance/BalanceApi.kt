package com.lotto.lottoapp.model.data.balance

import com.lotto.lottoapp.model.request.BalanceRequest
import com.lotto.lottoapp.model.response.balance.BalanceResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BalanceApi @Inject constructor(private val service: BalanceService) {
    suspend fun addBalance(
        balanceAmount: BalanceRequest,
        token: String,
    ): Response<BalanceResponse> =
        service.addBalance(
            token = "token $token", balanceAmount = balanceAmount,

            )

    suspend fun getBalance(token: String): Response<BalanceResponse> =
        service.getBalance(token = "token $token")
}


