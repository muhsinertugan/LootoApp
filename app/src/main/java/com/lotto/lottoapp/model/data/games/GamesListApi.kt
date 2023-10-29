package com.lotto.lottoapp.model.data.games

import com.lotto.lottoapp.model.response.home.GamesListResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesListApi @Inject constructor(private val service: GamesListService){
    suspend fun getGamesList(): Response<GamesListResponse> = service.getGamesList()
}