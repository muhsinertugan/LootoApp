package com.lotto.lottoapp.model.response.login

data class LoginResponseItem(
    val `data`: LoginData,
    val message: String,
    val success: Boolean,
) {
    var code = 0
}


data class LoginData(
    val email: String,
)

