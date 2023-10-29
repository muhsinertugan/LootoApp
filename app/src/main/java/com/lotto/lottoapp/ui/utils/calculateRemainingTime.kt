package com.lotto.lottoapp.ui.utils

import java.time.Duration
import java.time.Instant

fun calculateRemainingTime(targetDate: String): String {
    val targetInstant = Instant.parse(targetDate)
    val currentInstant = Instant.now()
    val remainingTime = Duration.between(currentInstant, targetInstant)

    val days = remainingTime.toDays()
    val hours = remainingTime.toHours() % 24
    val minutes = remainingTime.toMinutes() % 60
    //val seconds = remainingTime.seconds % 60

    return "${days}d ${hours}h ${minutes}m"
}
