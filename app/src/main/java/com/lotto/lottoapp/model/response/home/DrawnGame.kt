package com.lotto.lottoapp.model.response.home

data class DrawnGame(
    val _id: String,
    val gameCode: String,
    val name: String,
    val prize: Int,
    val image: String
)