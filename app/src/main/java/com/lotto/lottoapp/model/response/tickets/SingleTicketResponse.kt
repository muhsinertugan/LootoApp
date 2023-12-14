package com.lotto.lottoapp.model.response.tickets

data class SingleTicketResponse(
    val currency: String,
    val game: String,
    val guessedNumbers: Int,
    val isWinner: Boolean,
    val prize: Int,
    val success: Boolean,
    val ticketCode: String
)