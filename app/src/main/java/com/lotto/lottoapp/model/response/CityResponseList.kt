package com.lotto.lottoapp.model.response

data class CityResponseList(
    val `data`: List<CityResponseItem>,
    val records: Int,
    val success: Boolean
)
