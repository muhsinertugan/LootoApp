package com.lotto.lottoapp.model.response.balance

data class BalanceResponse(
    val balance: Int,
    val date: Long,
    val email: String,
    val success: Boolean,
    val userId: String
)