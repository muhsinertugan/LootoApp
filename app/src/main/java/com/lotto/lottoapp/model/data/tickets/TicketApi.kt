package com.lotto.lottoapp.model.data.tickets

import com.lotto.lottoapp.model.request.BuyTicketRequest
import com.lotto.lottoapp.model.response.tickets.BuyTicketResponse
import com.lotto.lottoapp.model.response.tickets.UserTicketsResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketApi @Inject constructor(private val service: TicketService){
    suspend fun postBuyTicket(ticket: BuyTicketRequest, token: String): Response<BuyTicketResponse> = service.postTicket(
        token= "token $token",
        ticketRequest = ticket
    )
    suspend fun getUserTickets(token: String): Response<UserTicketsResponse> = service.getUserTickets(token = "token $token")
}