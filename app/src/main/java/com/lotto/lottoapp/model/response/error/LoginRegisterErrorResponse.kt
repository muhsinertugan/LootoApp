package com.lotto.lottoapp.model.response.error

data class LoginRegisterErrorResponse(
    val code: Int,
    val message: String,
    val success: Boolean
)