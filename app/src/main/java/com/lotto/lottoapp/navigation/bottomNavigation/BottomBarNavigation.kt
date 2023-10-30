package com.lotto.lottoapp.navigation.bottomNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController


@Composable
fun BottomBarNavigation(navController: NavHostController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Results,
        BottomNavItem.Profile,
        )

    NavigationBar(containerColor = Color.DarkGray) {
        items.forEach { item ->
            NavigationBarItem(
                selected = navController.currentDestination!!.route.toString() == item.route,
                onClick = {

                    navController.navigate(item.route)

                },
                icon = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(

                            imageVector = item.icon,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(text = item.title, color = Color.White)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Gray
                )
            )
        }
    }
}



