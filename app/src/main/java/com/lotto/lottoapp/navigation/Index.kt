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
        NavHost(navController = navController, startDestination = "result_screen") {

            //TODO: Re-do the route logic
            composable(route = "splash_screen") {
                SplashScreen(navController = navController)
            }
            composable(route = "login_screen") {
                LoginRegisterLayout(
                    inputComponent = {
                        LoginScreen(
                            navController = navController,
                        )
                    },
                    navController = navController,
                )
            }
            composable(route = "register_screen") {

                LoginRegisterLayout(
                    inputComponent = {
                        RegisterScreen(navController = navController)

                    }, navController = navController
                )
            }
            composable(
                route = "otp_screen/{email}", arguments = listOf(
                    navArgument("email") { nullable = true },
                    navArgument("birthDate") { nullable = true },
                    navArgument("cityId") { nullable = true },
                    navArgument("lastName") { nullable = true },
                    navArgument("name") { nullable = true },
                    navArgument("phoneNumber") { nullable = true },
                )
            ) { it ->
                val email = it.arguments?.getString("email")?.takeIf { it != "null" }
                val birthDate = it.arguments?.getString("birthDate")?.takeIf { it != "null" }
                val cityId = it.arguments?.getString("cityId")?.takeIf { it != "null" }
                val lastName = it.arguments?.getString("lastName")?.takeIf { it != "null" }
                val name = it.arguments?.getString("name")?.takeIf { it != "null" }
                val phoneNumber = it.arguments?.getString("phoneNumber")?.takeIf { it != "null" }


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
            composable(route = "home_screen") {
                GeneralLayout(
                    inputComponent = { HomeScreen(navController = navController) }, navController = navController
                )
            }
            composable(route = "profile_screen") {
                GeneralLayout(
                    inputComponent = { ProfileScreen() }, navController = navController
                )
            }
            composable(route = "game_screen/{gameId}", arguments = listOf(
                navArgument("gameId") { nullable = true },
            )) {
                GeneralLayout(
                    inputComponent = { GameScreen() }, navController = navController
                )
            }
            composable(route = "result_screen") {
                GeneralLayout(
                    inputComponent = { ResultScreen() }, navController = navController
                )
            }
        }
    }


}