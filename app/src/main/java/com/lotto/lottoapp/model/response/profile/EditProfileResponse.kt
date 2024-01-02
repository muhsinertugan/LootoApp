package com.lotto.lottoapp.model.response.profile

data class EditProfileResponse(
    val message: String,
    val success: Boolean,
    val user: User,
)