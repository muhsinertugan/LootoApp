package com.lotto.lottoapp.model.data

import com.lotto.lottoapp.model.response.CityResponseList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralApi @Inject constructor(private val service: GeneralService){


    suspend fun getCities(): CityResponseList = service.getCities()


}