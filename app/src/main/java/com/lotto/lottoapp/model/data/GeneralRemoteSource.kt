package com.lotto.lottoapp.model.data

import com.lotto.lottoapp.model.CityItem
import com.lotto.lottoapp.model.response.general.CityResponseList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralRemoteSource @Inject constructor(private val cityApi: GeneralApi) {

    private fun CityResponseList.mapCityToList(): List<CityItem>? {
        return this.data.map { cities ->
            CityItem(
                id = cities._id,
                name   = cities.name
            )
        }
    }
    suspend fun getCities(): List<CityItem>? = withContext(Dispatchers.IO) {
        return@withContext cityApi.getCities().mapCityToList()
    }

}