package com.lotto.lottoapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lotto.lottoapp.ui.layout.LoginRegisterLayout
import com.lotto.lottoapp.ui.feature.login.LoginScreen
import com.lotto.lottoapp.ui.feature.otp.OtpScreen
import com.lotto.lottoapp.ui.feature.register.RegisterScreen
import com.lotto.lottoapp.ui.feature.register.RegisterScreenViewModel
import com.lotto.lottoapp.ui.feature.splash.SplashScreen
import com.lotto.lottoapp.ui.layout.OtpScreenLayout

@Composable
fun Index()
{
    val registerScreenViewModel: RegisterScreenViewModel = hiltViewModel()
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "register_screen"){
            composable(route = "splash_screen"){
                SplashScreen(navController= navController)
            }
            composable(route = "login_screen"){
                LoginRegisterLayout(inputComponent = { LoginScreen(navController= navController) },navController= navController )
            }
            composable(route = "register_screen"){
                LoginRegisterLayout(inputComponent = { RegisterScreen( navController= navController, state = registerScreenViewModel.state) },navController= navController )
            }
            composable(route = "otp_screen"){
                OtpScreenLayout( inputComponent = { OtpScreen(navController = navController)  },navController= navController )
            }
        }
    }
    
    
}