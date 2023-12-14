package com.lotto.lottoapp.model.data.tickets

import com.lotto.lottoapp.di.ENDPOINTS
import com.lotto.lottoapp.model.request.BuyTicketRequest
import com.lotto.lottoapp.model.response.tickets.BuyTicketResponse
import com.lotto.lottoapp.model.response.tickets.SingleTicketResponse
import com.lotto.lottoapp.model.response.tickets.UserTicketsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface TicketService {
    @POST(ENDPOINTS.TICKETS_URL)
    suspend fun postTicket(
        @Body ticketRequest: BuyTicketRequest,
        @Header("Authorization") token: String
    ): Response<BuyTicketResponse>

    @GET(ENDPOINTS.TICKETS_URL)
    suspend fun getUserTickets(@Header("Authorization") token: String): Response<UserTicketsResponse>

    @GET("${ENDPOINTS.TICKETS_URL}/{ticketNumber}")
    suspend fun getTicketResult(
        @Header("Authorization") token: String,
        @Path("ticketNumber") ticketNumber: String
    ): Response<SingleTicketResponse>

}