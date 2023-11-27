package com.lotto.lottoapp.model.response.tickets

data class Ticket(
    val __v: Int,
    val _id: String,
    val blocks: List<TicketNumbers>,
    val createdAt: String,
    val game: String,
    val hasDrawn: Boolean,
    val ticketCode: String,
    val user: String
)