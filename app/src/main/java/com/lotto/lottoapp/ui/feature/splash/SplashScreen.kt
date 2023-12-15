package com.lotto.lottoapp.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lotto.lottoapp.R
import com.lotto.lottoapp.navigation.NavigationItems
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(
    navController: NavController,
    isUserSignedIn: Boolean,
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel()
) {
    val cityState = splashScreenViewModel.cityState.collectAsState()
    LaunchedEffect(key1 = cityState.value.isLoading) {
        this.launch {

            if (!isUserSignedIn) {
                navController.navigate(NavigationItems.Auth.route) {
                    popUpTo(NavigationItems.Splash.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(
                    NavigationItems.App.route
                ) {
                    popUpTo(NavigationItems.Splash.route) {
                        inclusive = true
                    }
                }
            }


        }
    }
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = "Splash Screen",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds

        )

    }
}

