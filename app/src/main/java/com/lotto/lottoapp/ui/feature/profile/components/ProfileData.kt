package com.lotto.lottoapp.ui.feature.profile.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun ProfileData(title: String, data: String) {
    Row {
        Text(
            text = "$title: ",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFFFFFFFF),
            )
        )
        Text(
            text = data,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFFFFFFFF),
            )
        )
    }
}