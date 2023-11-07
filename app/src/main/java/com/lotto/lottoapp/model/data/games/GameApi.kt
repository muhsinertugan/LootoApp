package com.lotto.lottoapp.model.data.games

import com.lotto.lottoapp.model.response.game.Game
import com.lotto.lottoapp.model.response.game.GameResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameApi @Inject constructor(private val service: GameService){
    suspend fun getGame(gameId: String): Response<GameResponse> = service.getGame(id = gameId)
}