package com.lotto.lottoapp.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lotto.lottoapp.R
import com.lotto.lottoapp.components.BackgroundImage

@Composable
fun LoginRegisterLayout(inputComponent: @Composable() ()->Unit) {
    BackgroundImage()
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
    inputComponent()
    Box(modifier = Modifier.height(125.dp) ){
        Box(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(bottom = 75.dp)){
            Image(painter = painterResource(id = R.drawable.lotto_logo),
                contentDescription = "logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(160.dp)
                    .height(50.dp)
                    .align(Alignment.BottomCenter)


            )
        }
    }
}


}

