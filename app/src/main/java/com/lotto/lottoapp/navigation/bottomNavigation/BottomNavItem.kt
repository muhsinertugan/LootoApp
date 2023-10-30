package com.lotto.lottoapp.navigation.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var route : String,
    var icon: ImageVector
)  {
    object Home :
        BottomNavItem(
            "Home",
            "home_screen",
            Icons.Outlined.Home
        )
    object Results :
        BottomNavItem(
            "Results",
            "profile_screen",
            Icons.Outlined.Search
        )
    object Profile :
        BottomNavItem(
            "Profile",
            "profile_screen",

            Icons.Outlined.Person
        )

}