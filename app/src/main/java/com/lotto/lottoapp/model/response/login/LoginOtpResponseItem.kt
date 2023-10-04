package com.lotto.lottoapp.model.response.login

data class LoginOtpResponseItem(
    val `data`: LoginOtpData,
    val message: String,
    val success: Boolean
)

data class LoginOtpData(
    val email: String,
    val token: String
)