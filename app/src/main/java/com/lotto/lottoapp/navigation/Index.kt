
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lotto.lottoapp.model.request.LoginRequest
import com.lotto.lottoapp.model.request.RegisterRequest
import com.lotto.lottoapp.navigation.NavigationItems
import com.lotto.lottoapp.navigation.ScreenArguments
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

@Composable
fun Index(
    sharedPreferencesUtil: SharedPreferencesUtil
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationItems.Splash.route) {
        val isUserSignedIn = checkUserAuthenticationState(sharedPreferencesUtil)

        composable(NavigationItems.Splash.route) {
            SplashScreen(navController = navController, isUserSignedIn = isUserSignedIn)
        }

        if (isUserSignedIn) {
            loginRegisterFlow(navController)
        }
            mainAppFlow(navController)

    }
}

private fun NavGraphBuilder.loginRegisterFlow(navController: NavHostController) {
    navigation(
        route = NavigationItems.Auth.route,
        startDestination = NavigationItems.Auth.Login.route,
    ) {
        composable(NavigationItems.Auth.Login.route) {
            LoginRegisterLayout(
                inputComponent = {
                    LoginScreen(navController = navController)
                },
                navController = navController,
            )
        }

        composable(NavigationItems.Auth.Register.route) {
            LoginRegisterLayout(
                inputComponent = {
                    RegisterScreen(navController = navController)
                },
                navController = navController
            )
        }

        composable(
            route = "${NavigationItems.Auth.Otp.route}/{${ScreenArguments.EMAIL}}",
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
                },
                navController = navController
            )
        }
    }
}

private fun NavGraphBuilder.mainAppFlow(navController: NavHostController) {
    navigation(
        route = NavigationItems.App.route,
        startDestination = NavigationItems.App.Home.route,
    ) {
        composable(NavigationItems.App.Home.route) {
            GeneralLayout(
                inputComponent = { HomeScreen(navController = navController) },
                navController = navController
            )
        }

        composable(
            route = "${NavigationItems.App.Home.Game.route}/{${ScreenArguments.GAME}}",
            arguments = listOf(
                navArgument(ScreenArguments.GAME) { nullable = true },
            )
        ) {
            GeneralLayout(
                inputComponent = { GameScreen() },
                navController = navController
            )
        }

        composable(NavigationItems.App.Results.route) {
            GeneralLayout(
                inputComponent = { ResultScreen() },
                navController = navController
            )
        }

        navigation(
            route = NavigationItems.App.Profile.route,
            startDestination = NavigationItems.App.Profile.Profile.route,
        ) {
            composable(NavigationItems.App.Profile.Profile.route) {
                GeneralLayout(
                    inputComponent = { ProfileScreen(navController = navController) },
                    navController = navController
                )
            }

            composable(NavigationItems.App.Profile.EditProfile.route) {
                GeneralLayout(
                    inputComponent = { EditProfileScreen() },
                    navController = navController
                )
            }
        }
    }
}

fun checkUserAuthenticationState(sharedPreferencesUtil: SharedPreferencesUtil): Boolean {
    val userToken = sharedPreferencesUtil.loadData("userToken")
    return userToken == ""
}
