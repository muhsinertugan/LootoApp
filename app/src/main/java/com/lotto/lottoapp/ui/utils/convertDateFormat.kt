package com.lotto.lottoapp.ui.utils

import androidx.compose.runtime.Composable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun convertDateFormat(dateString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val localDateTime = LocalDateTime.parse(dateString, inputFormatter)
    return localDateTime.format(outputFormatter)
}