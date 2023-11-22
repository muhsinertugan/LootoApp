package com.lotto.lottoapp.model.data.draws

import com.lotto.lottoapp.model.response.home.RecentDrawsResponse
import retrofit2.Response
import javax.inject.Inject

class RecentDrawsApi @Inject constructor(private val service: RecentDrawsService) {
    suspend fun getRecentDraws(): Response<RecentDrawsResponse> = service.getRecentDraws()
}