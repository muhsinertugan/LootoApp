package com.lotto.lottoapp.core.components

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
import androidx.compose.ui.zIndex
import com.lotto.lottoapp.R

@Composable
fun BackgroundImage(){
    Box( contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.screen_bg),
            contentDescription = "background image",
            modifier = Modifier.fillMaxSize().blur(30.dp).zIndex(-10F),
            contentScale = ContentScale.FillBounds

        )

    }
}