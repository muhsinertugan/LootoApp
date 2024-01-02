package com.lotto.lottoapp.utils

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeUtil {
    private companion object {
        val inputFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }

    fun calculateRemainingTime(targetDate: String): String {
        val targetInstant = Instant.parse(targetDate)
        val currentInstant = Instant.now()
        val remainingTime = Duration.between(currentInstant, targetInstant)

        val days = remainingTime.toDays()
        val hours = remainingTime.toHours() % 24
        val minutes = remainingTime.toMinutes() % 60

        return "${days}d ${hours}h ${minutes}m"
    }

    fun convertDateFormat(dateString: String): String {

        val localDateTime = LocalDateTime.parse(dateString, inputFormatter)
        return localDateTime.format(outputFormatter)
    }


}