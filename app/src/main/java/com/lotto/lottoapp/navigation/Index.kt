@file:Suppress("SameParameterValue")

package com.lotto.lottoapp.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lotto.lottoapp.model.request.LoginRequest
import com.lotto.lottoapp.model.request.RegisterRequest
import com.lotto.lottoapp.ui.feature.editProfile.EditProfileScreen
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
import com.lotto.lottoapp.utils.SharedPreferencesUtil

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Index(
    sharedPreferencesUtil: SharedPreferencesUtil,
) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isUserSignedIn = checkUserAuthenticationState(sharedPreferencesUtil)

    NavHost(navController = navController, startDestination = NavigationItems.Splash.route) {
        composable(NavigationItems.Splash.route) {
            SplashScreen(navController = navController, isUserSignedIn = isUserSignedIn)
        }

        loginRegisterFlow(navController, snackbarHostState, keyboardController)
        mainAppFlow(navController, snackbarHostState, keyboardController)


    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.loginRegisterFlow(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController?,
) {
    navigation(
        route = NavigationItems.Auth.route,
        startDestination = NavigationItems.Auth.Login.route,
    ) {
        composable(NavigationItems.Auth.Login.route) {
            LoginRegisterLayout(
                inputComponent = {
                    LoginScreen(
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        keyboardController = keyboardController
                    )
                },
                navController = navController, snackbarHostState = snackbarHostState,
            )
        }

        composable(NavigationItems.Auth.Register.route) {
            LoginRegisterLayout(
                inputComponent = {
                    RegisterScreen(
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        keyboardController = keyboardController
                    )
                },
                navController = navController, snackbarHostState = snackbarHostState
            )
        }


        composable(
            route = "${NavigationItems.Auth.Otp.route}/{${ScreenArguments.EMAIL}}",
            arguments = listOf(
                navArgument("{${ScreenArguments.EMAIL}}") { nullable = true },
                navArgument("{${ScreenArguments.NAME}}") { nullable = true },
                navArgument("{${ScreenArguments.SURNAME}}") { nullable = true },
                navArgument("{${ScreenArguments.PHONE}}") { nullable = true },
                navArgument("{${ScreenArguments.CITY}}") { nullable = true },
                navArgument("{${ScreenArguments.BIRTHDATE}}") { nullable = true },
            )
        ) { navBackStackEntry ->
            val args = navBackStackEntry.arguments
            val email = args?.getString(ScreenArguments.EMAIL)?.takeIf { it != "null" }
            val birthDate = args?.getString(ScreenArguments.BIRTHDATE)?.takeIf { it != "null" }
            val cityId = args?.getString(ScreenArguments.CITY)?.takeIf { it != "null" }
            val lastName = args?.getString(ScreenArguments.SURNAME)?.takeIf { it != "null" }
            val name = args?.getString(ScreenArguments.NAME)?.takeIf { it != "null" }
            val phoneNumber = args?.getString(ScreenArguments.PHONE)?.takeIf { it != "null" }


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
                        userRegisterInput = registerInput,
                        snackbarHostState = snackbarHostState,
                        keyboardController = keyboardController
                    )
                },
                navController = navController, snackbarHostState = snackbarHostState,
            )
        }

        composable(
            route = "${NavigationItems.Auth.Otp.route}/{${ScreenArguments.EMAIL}}/{${ScreenArguments.NAME}}/{${ScreenArguments.SURNAME}}/{${ScreenArguments.PHONE}}/{${ScreenArguments.CITY}}/{${ScreenArguments.BIRTHDATE}}",
            arguments = listOf(
                navArgument("{${ScreenArguments.EMAIL}}") { nullable = true },
                navArgument("{${ScreenArguments.NAME}}") { nullable = true },
                navArgument("{${ScreenArguments.SURNAME}}") { nullable = true },
                navArgument("{${ScreenArguments.PHONE}}") { nullable = true },
                navArgument("{${ScreenArguments.CITY}}") { nullable = true },
                navArgument("{${ScreenArguments.BIRTHDATE}}") { nullable = true },
            )
        ) { navBackStackEntry ->
            val args = navBackStackEntry.arguments
            val email = args?.getString(ScreenArguments.EMAIL)?.takeIf { it != "null" }
            val birthDate = args?.getString(ScreenArguments.BIRTHDATE)?.takeIf { it != "null" }
            val cityId = args?.getString(ScreenArguments.CITY)?.takeIf { it != "null" }
            val lastName = args?.getString(ScreenArguments.SURNAME)?.takeIf { it != "null" }
            val name = args?.getString(ScreenArguments.NAME)?.takeIf { it != "null" }
            val phoneNumber = args?.getString(ScreenArguments.PHONE)?.takeIf { it != "null" }

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
                        userRegisterInput = registerInput,
                        snackbarHostState = snackbarHostState,
                        keyboardController = keyboardController
                    )
                },
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
private fun NavGraphBuilder.mainAppFlow(
    navController: NavHostController, snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController?,
) {
    navigation(
        route = NavigationItems.App.route,
        startDestination = NavigationItems.App.Home.route,
    ) {
        composable(NavigationItems.App.Home.route) {
            GeneralLayout(
                inputComponent = {
                    HomeScreen(
                        navController = navController, snackbarHostState = snackbarHostState,
                        keyboardController = keyboardController
                    )
                },
                navController = navController, snackbarHostState = snackbarHostState,
            )
        }

        composable(
            route = "${NavigationItems.App.Home.Game.route}/{${ScreenArguments.GAME}}",
            arguments = listOf(
                navArgument(ScreenArguments.GAME) { nullable = true },
            )
        ) {
            GeneralLayout(
                inputComponent = {
                    GameScreen(
                        snackbarHostState = snackbarHostState,
                        keyboardController = keyboardController
                    )
                },
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }

        composable(NavigationItems.App.Results.route) {
            GeneralLayout(
                inputComponent = {
                    ResultScreen(
                        snackbarHostState = snackbarHostState,
                        keyboardController = keyboardController
                    )
                },
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }

        navigation(
            route = NavigationItems.App.Profile.route,
            startDestination = NavigationItems.App.Profile.Profile.route,
        ) {
            composable(NavigationItems.App.Profile.Profile.route) {
                GeneralLayout(
                    inputComponent = {
                        ProfileScreen(
                            navController = navController, snackbarHostState = snackbarHostState,
                            keyboardController = keyboardController
                        )
                    },
                    navController = navController,
                    snackbarHostState = snackbarHostState
                )
            }

            composable(NavigationItems.App.Profile.EditProfile.route) {
                GeneralLayout(
                    inputComponent = {
                        EditProfileScreen(
                            navController = navController, snackbarHostState = snackbarHostState,
                            keyboardController = keyboardController
                        )
                    },
                    navController = navController,
                    snackbarHostState = snackbarHostState
                )
            }
        }
    }
}

fun checkUserAuthenticationState(sharedPreferencesUtil: SharedPreferencesUtil): Boolean {
    val userToken: String = sharedPreferencesUtil.loadData<String>("userToken", "")

    return userToken.isNotBlank()

}

