package com.lotto.lottoapp.model.request

data class LoginRequest(
    val email: String?
)

data class LoginOtpRequest(
    val email: String?,
    val otp: String
)