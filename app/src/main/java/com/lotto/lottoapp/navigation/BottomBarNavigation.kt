package com.lotto.lottoapp.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun BottomBarNavigation() {

    NavigationBar {

        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = ""
                )
                Text(text = "Home")
            }
        })
        NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = ""
                )
                Text(text = "Results")
            }
        })
        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = ""
                )
                Text(text = "Profile")
            }
        })

    }
}