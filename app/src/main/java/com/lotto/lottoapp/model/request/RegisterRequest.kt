package com.lotto.lottoapp.model.request

data class RegisterRequest(
    var birthDate: String?,
    var cityId: String?,
    var email: String?,
    var lastName: String?,
    var name: String?,
    var phoneNumber: String?,
)

data class RegisterOtpRequest(
    val birthDate: String?,
    val cityId: String?,
    val email: String?,
    val lastName: String?,
    val name: String?,
    val phoneNumber: String?,
    val otp: String,
)