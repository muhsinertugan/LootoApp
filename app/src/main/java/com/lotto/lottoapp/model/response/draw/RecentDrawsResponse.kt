package com.lotto.lottoapp.model.response.draw

data class RecentDrawsResponse(
    val count: Int,
    val draws: List<Draw>,
    val success: Boolean,
)