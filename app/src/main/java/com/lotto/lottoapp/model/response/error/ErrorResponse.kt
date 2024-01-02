package com.lotto.lottoapp.model.response.error

data class ErrorResponse(
    val code: Int,
    val message: String,
    val success: Boolean,
)