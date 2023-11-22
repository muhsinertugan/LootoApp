package com.lotto.lottoapp.model.data.tickets

import com.lotto.lottoapp.di.ENDPOINTS
import com.lotto.lottoapp.model.request.BuyTicketRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TicketService {
    @POST(ENDPOINTS.TICKETS_URL)
    suspend fun postTicket(@Body ticketRequest: BuyTicketRequest): Response<>

    @GET(ENDPOINTS.TICKETS_URL)
    suspend fun getUserTickets(@Header("Authorization") token: String): Response<>

}