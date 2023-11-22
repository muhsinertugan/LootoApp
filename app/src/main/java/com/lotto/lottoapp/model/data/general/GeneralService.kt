package com.lotto.lottoapp.model.data.general

import com.lotto.lottoapp.di.ENDPOINTS
import com.lotto.lottoapp.model.response.general.CityResponseList
import retrofit2.http.GET


interface GeneralService {
    @GET(ENDPOINTS.CITIES_URL)
    suspend fun getCities(): CityResponseList
}