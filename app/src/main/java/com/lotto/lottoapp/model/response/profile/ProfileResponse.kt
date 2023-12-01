package com.lotto.lottoapp.model.response.profile

data class ProfileResponse(
    val `data`: User,
    val message: String,
    val success: Boolean
)