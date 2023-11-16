package com.lotto.lottoapp.model.data.games

import com.lotto.lottoapp.model.request.BuyTicketRequest
import com.lotto.lottoapp.model.response.login.LoginResponseItem
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketApi @Inject constructor(private val service: TicketService){
    suspend fun postTicket(ticket: BuyTicketRequest): Response<LoginResponseItem> = service.postTicket(
        ticketRequest = ticket
    )
}