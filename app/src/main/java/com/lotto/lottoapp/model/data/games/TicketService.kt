package com.lotto.lottoapp.model.data.games

import com.lotto.lottoapp.model.request.BuyTicketRequest
import com.lotto.lottoapp.model.response.login.LoginResponseItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TicketService {
    @POST("tickets")
    suspend fun postTicket(@Body ticketRequest: BuyTicketRequest): Response<LoginResponseItem>
}