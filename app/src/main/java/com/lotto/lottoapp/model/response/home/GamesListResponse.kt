package com.lotto.lottoapp.model.response.home

data class GamesListResponse(
    val gameList: List<Game>,
    val success: Boolean
)