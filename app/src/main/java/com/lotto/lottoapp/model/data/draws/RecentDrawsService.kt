package com.lotto.lottoapp.model.data.draws

import com.lotto.lottoapp.di.ENDPOINTS
import com.lotto.lottoapp.model.response.home.RecentDrawsResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecentDrawsService {
    @GET(ENDPOINTS.DRAWS_URL)
    suspend fun getRecentDraws(): Response<RecentDrawsResponse>
}