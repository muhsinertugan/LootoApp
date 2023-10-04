package com.lotto.lottoapp.ui.feature.otp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomCountDownTimer(totalDuration: Float, remainingTime: Float){



    Box(modifier = Modifier
        .width(96.dp)
        .height(96.dp)) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Box {
                CircularProgressIndicator(modifier = Modifier.size(86.dp), progress = (remainingTime / totalDuration), strokeWidth = 5.dp, color = Color(red = 204, green = 0, blue = 205))
            }
        }
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

            Text(text = "${remainingTime.toInt()}", style= MaterialTheme.typography.displaySmall, textAlign = TextAlign.Center, color = Color.White)
        }
    }
}