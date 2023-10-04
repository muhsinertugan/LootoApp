package com.lotto.lottoapp.model.response

data class LoginRegisterApiResponse<T>(
    val `data`: T,
    val message: String,
    val success: Boolean
)
