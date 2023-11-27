package com.lotto.lottoapp.model.response.tickets

data class UserTicketsResponse(
    val `data`: Tickets,
    val message: String,
    val success: Boolean
)