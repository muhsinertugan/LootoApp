package com.lotto.lottoapp.ui.feature.home

import com.lotto.lottoapp.model.response.home.Draw
import com.lotto.lottoapp.model.response.home.Game

class HomeScreenContract {

    data class GamesListState(
        val games: List<Game> = listOf(),
        val success: Boolean
    )

    data class RecentDrawsState(
        val recentDraws: List<Draw> = listOf(),
        val count: Int,
        val success: Boolean
    )

}