package com.lotto.lottoapp.ui.feature.otp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lotto.lottoapp.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun ResendBtn(handleResend: () -> Unit) {
    val scope = rememberCoroutineScope()


    Box(modifier = Modifier.padding(vertical = 120.dp)) {

        Text(
            text = "Resend Code",
            style = Typography.titleLarge.copy(color = Color.White),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable {
                    scope.launch {
                        handleResend()
                    }
                }
                .clip(RoundedCornerShape(28.dp))
                .background(color = Color(red = 79, green = 79, blue = 79))
                .padding(vertical = 16.dp, horizontal = 30.dp)
        )
    }
    Box {
        Text(
            text = "Your time run out",
            style = Typography.titleLarge.copy(color = Color.White),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(28.dp))
                .padding(vertical = 16.dp, horizontal = 30.dp)
        )
    }
}