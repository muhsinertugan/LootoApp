package com.lotto.lottoapp.model.response.tickets

data class TicketNumbers(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val isWinner: Boolean,
    val numbers: List<Int>,
    val ticket: String,
)