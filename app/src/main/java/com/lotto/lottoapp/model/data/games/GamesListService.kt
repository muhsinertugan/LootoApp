package com.lotto.lottoapp.model.data.games

import com.lotto.lottoapp.di.ENDPOINTS
import com.lotto.lottoapp.model.response.home.GamesListResponse
import retrofit2.Response
import retrofit2.http.GET

interface GamesListService {
    @GET(ENDPOINTS.GAMES_URL)
    suspend fun getGamesList(): Response<GamesListResponse>
}