package com.lotto.lottoapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lotto.lottoapp.R

@Composable
fun BackgroundImage(){
    Box( contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.screen_bg),
            contentDescription = "Splash Screen",
            modifier = Modifier.fillMaxSize().blur(10.dp),
            contentScale = ContentScale.FillBounds

        )

    }
}