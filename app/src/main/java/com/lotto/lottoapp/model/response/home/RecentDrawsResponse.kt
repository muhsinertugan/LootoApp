package com.lotto.lottoapp.model.response.home

data class RecentDrawsResponse(
    val count: Int,
    val draws: List<Draw>,
    val success: Boolean
)