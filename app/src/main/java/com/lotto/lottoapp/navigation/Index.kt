package com.lotto.lottoapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lotto.lottoapp.model.request.LoginRequest
import com.lotto.lottoapp.model.request.RegisterRequest
import com.lotto.lottoapp.ui.feature.game.GameScreen
import com.lotto.lottoapp.ui.feature.home.HomeScreen
import com.lotto.lottoapp.ui.feature.login.LoginScreen
import com.lotto.lottoapp.ui.feature.otp.OtpScreen
import com.lotto.lottoapp.ui.feature.profile.ProfileScreen
import com.lotto.lottoapp.ui.feature.register.RegisterScreen
import com.lotto.lottoapp.ui.feature.result.ResultScreen
import com.lotto.lottoapp.ui.feature.splash.SplashScreen
import com.lotto.lottoapp.ui.layout.GeneralLayout
import com.lotto.lottoapp.ui.layout.LoginRegisterLayout
import com.lotto.lottoapp.ui.layout.OtpScreenLayout

@Composable
fun Index() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Paths.SPLASH_SCREEN) {

            composable(route = Paths.SPLASH_SCREEN) {
                SplashScreen(navController = navController)
            }
            composable(route = Paths.LOGIN_SCREEN) {
                LoginRegisterLayout(
                    inputComponent = {
                        LoginScreen(
                            navController = navController,
                        )
                    },
                    navController = navController,
                )
            }
            composable(route = Paths.REGISTER_SCREEN) {
                LoginRegisterLayout(
                    inputComponent = {
                        RegisterScreen(navController = navController)

                    }, navController = navController
                )
            }
            composable(
                route = "${Paths.OTP_SCREEN}/{${ScreenArguments.EMAIL}}",
                arguments = listOf(
                    navArgument(ScreenArguments.EMAIL) { nullable = true },
                    navArgument(ScreenArguments.BIRTHDATE) { nullable = true },
                    navArgument(ScreenArguments.CITY) { nullable = true },
                    navArgument(ScreenArguments.SURNAME) { nullable = true },
                    navArgument(ScreenArguments.NAME) { nullable = true },
                    navArgument(ScreenArguments.PHONE) { nullable = true },
                )
            ) { navBackStackEntry ->
                val args = navBackStackEntry.arguments
                val email = args?.getString(ScreenArguments.EMAIL)
                val birthDate = args?.getString(ScreenArguments.BIRTHDATE)
                val cityId = args?.getString(ScreenArguments.CITY)
                val lastName = args?.getString(ScreenArguments.SURNAME)
                val name = args?.getString(ScreenArguments.NAME)
                val phoneNumber = args?.getString(ScreenArguments.PHONE)

                val registerInput = RegisterRequest(
                    birthDate = birthDate,
                    cityId = cityId,
                    email = email,
                    lastName = lastName,
                    name = name,
                    phoneNumber = phoneNumber
                )
                val loginInput = LoginRequest(email = email)

                OtpScreenLayout(
                    inputComponent = {

                        OtpScreen(
                            navController = navController,
                            userLoginInput = loginInput,
                            userRegisterInput = registerInput
                        )
                    }, navController = navController
                )
            }
            composable(route = Paths.HOME_SCREEN) {
                GeneralLayout(
                    inputComponent = { HomeScreen(navController = navController) },
                    navController = navController
                )
            }
            composable(route = Paths.PROFILE_SCREEN) {
                GeneralLayout(
                    inputComponent = { ProfileScreen() }, navController = navController
                )
            }
            composable(
                route = "${Paths.GAME_SCREEN}/{${ScreenArguments.GAME}}", arguments = listOf(
                    navArgument(ScreenArguments.GAME) { nullable = true },
                )
            ) {
                GeneralLayout(
                    inputComponent = { GameScreen() }, navController = navController
                )
            }
            composable(route = Paths.RESULT_SCREEN) {
                GeneralLayout(
                    inputComponent = { ResultScreen() }, navController = navController
                )
            }
        }
    }


}