package com.lotto.lottoapp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lotto.lottoapp.layout.LoginRegisterLayout
import com.lotto.lottoapp.screens.LoginScreen
import com.lotto.lottoapp.screens.RegisterScreen
import com.lotto.lottoapp.screens.SplashScreen

@Composable
fun LottoApp()
{
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "register_screen"){
            composable(route = "splash_screen"){
                SplashScreen(navController= navController)
            }
            composable(route = "login_screen"){
                LoginRegisterLayout(inputComponent = { LoginScreen() })
            }
            composable(route = "register_screen"){
                LoginRegisterLayout(inputComponent = { RegisterScreen() })
            }
        }
    }
    
    
}