package com.lotto.lottoapp.model.response.general

data class SerializableCityState(
    val cities: List<CityResponseItem>,
    val isLoading: Boolean,
)