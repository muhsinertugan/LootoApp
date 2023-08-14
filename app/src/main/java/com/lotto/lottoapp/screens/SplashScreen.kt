package com.lotto.lottoapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.lotto.lottoapp.R
import kotlinx.coroutines.delay



@Composable
fun SplashScreen (navController: NavController){
    LaunchedEffect(key1 = true,  ){
        delay(5000L)
        navController.navigate("login_screen")
    }
    Box( contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
        ){
        Image(painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = "Splash Screen",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds

        )

    }
}

