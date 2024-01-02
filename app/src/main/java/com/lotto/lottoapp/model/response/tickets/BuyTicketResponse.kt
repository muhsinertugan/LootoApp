package com.lotto.lottoapp.model.response.tickets

data class BuyTicketResponse(
    val balance: Int,
    val game: String,
    val gameName: String,
    val message: String,
    val numbers: List<List<Int>>,
    val success: Boolean,
    val ticketCode: String,
)