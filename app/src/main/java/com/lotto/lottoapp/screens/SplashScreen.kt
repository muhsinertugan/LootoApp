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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lotto.lottoapp.R
import kotlinx.coroutines.delay

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"){
        composable(route = "splash_screen"){
             SplashScreen(navController= navController)
        }
        composable(route = "main_screen"){
            LoginScreen()
        }
    }
}

@Composable
fun SplashScreen (navController: NavController){
    LaunchedEffect(key1 = true,  ){
        delay(5000L)
        navController.navigate("main_screen")
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

@Preview
@Composable
fun PreviewSplashScreen(){
    Navigation()
}