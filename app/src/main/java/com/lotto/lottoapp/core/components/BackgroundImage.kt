package com.lotto.lottoapp.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.lotto.lottoapp.ui.theme.CustomBlack
import com.lotto.lottoapp.ui.theme.CustomPurple

@Composable
fun BackgroundImage(){

    val horizontalGradient = Brush.verticalGradient(
        colors = listOf(CustomPurple, CustomBlack, CustomBlack, CustomBlack),
        )

    Box( contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(brush = horizontalGradient)
    ){

    }
}
