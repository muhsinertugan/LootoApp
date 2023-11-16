package com.lotto.lottoapp.model.request

import com.lotto.lottoapp.ui.feature.game.GameScreenContract

data class BuyTicketRequest(
    val game: String,
    val numbers: List<List<GameScreenContract.SelectedNumbers>>
)