package com.lotto.lottoapp.model.response.profile

data class User(
    val __v: Int,
    val _id: String,
    val activated: Boolean,
    val balance: Int,
    val birthDate: String,
    val cityId: String,
    val createdAt: String,
    val email: String,
    val expireAt: String,
    val lastName: String,
    val name: String,
    val phoneNumber: String,
    val privacyPolicy: Boolean,
)