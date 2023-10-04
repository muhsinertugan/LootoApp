package com.lotto.lottoapp.model.data

import com.lotto.lottoapp.model.response.general.CityResponseList
import retrofit2.http.GET

interface GeneralService {
    @GET("cities")
    suspend fun getCities(): CityResponseList
}