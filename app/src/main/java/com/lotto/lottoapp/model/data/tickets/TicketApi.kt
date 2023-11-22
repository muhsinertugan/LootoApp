package com.lotto.lottoapp.model.data.tickets

import com.lotto.lottoapp.model.request.BuyTicketRequest
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketApi @Inject constructor(private val service: TicketService){
    suspend fun postTicket(ticket: BuyTicketRequest): Response<> = service.postTicket(
        ticketRequest = ticket
    )
    suspend fun getUserTickets(token: String): Response<> = service.getUserTickets(token = "token $token")
}