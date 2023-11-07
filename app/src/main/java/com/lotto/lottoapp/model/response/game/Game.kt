package com.lotto.lottoapp.model.response.game

data class Game(
    val __v: Int,
    val _id: String,
    val columnPrice: Int,
    val createdAt: String,
    val cronExpression: String,
    val cronExpressionDescription: String,
    val currency: String,
    val description: String,
    val extraNumbers: Int,
    val gameCode: String,
    val gameType: String,
    val image: String,
    val maximumNumber: Int,
    val mininumNumber: Int,
    val name: String,
    val nextDrawDate: String,
    val prize: Int,
    val requriedNumbers: Int,
    val status: String
)