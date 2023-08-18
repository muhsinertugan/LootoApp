package com.lotto.lottoapp.model.response


data class CityResponseItem(
    val __v: Int,
    val _id: String,
    val code: Int,
    val latitude: String,
    val longitude: String,
    val name: String,
    val population: Int,
    val region: String
)