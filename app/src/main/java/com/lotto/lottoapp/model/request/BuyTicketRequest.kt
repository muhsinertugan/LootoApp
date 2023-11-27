package com.lotto.lottoapp.model.request

data class BuyTicketRequest(
    val game: String,
    val numbers: List<Array<Int?>>
)