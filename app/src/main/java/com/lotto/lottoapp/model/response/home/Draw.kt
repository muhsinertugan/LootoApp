package com.lotto.lottoapp.model.response.home

data class Draw(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val extraNumbers: List<Any>,
    val game: DrawnGame,
    val numbers: List<Int>
)