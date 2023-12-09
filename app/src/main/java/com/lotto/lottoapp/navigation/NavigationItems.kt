package com.lotto.lottoapp.navigation

sealed class NavigationItems(val route: String) {
    object Splash : NavigationItems(Paths.SPLASH_SCREEN)
    object Auth : NavigationItems(Paths.LOGIN_REGISTER) {
        object Login : NavigationItems(Paths.LOGIN_SCREEN)
        object Register : NavigationItems(Paths.REGISTER_SCREEN)
        object Otp : NavigationItems(Paths.OTP_SCREEN)

    }

    object App : NavigationItems(Paths.APP) {
        object Home : NavigationItems(Paths.HOME_SCREEN) {
            object Game : NavigationItems(Paths.GAME_SCREEN)

        }

        object Results : NavigationItems(Paths.RESULT_SCREEN)
        object Profile : NavigationItems(Paths.PROFILE) {
            object Profile : NavigationItems(Paths.PROFILE_SCREEN)

            object EditProfile : NavigationItems(Paths.EDIT_PROFILE_SCREEN)
        }

    }
}