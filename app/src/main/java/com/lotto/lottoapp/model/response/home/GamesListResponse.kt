package com.lotto.lottoapp.model.response.home

import com.lotto.lottoapp.model.response.game.Game

data class GamesListResponse(
    val gameList: List<Game>,
    val success: Boolean,
)