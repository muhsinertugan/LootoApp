package com.lotto.lottoapp.model.data.games

import com.lotto.lottoapp.model.response.game.GameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GameService {
    @GET("games/{id}")
    suspend fun getGame(@Path("id") id: String): Response<GameResponse>
}