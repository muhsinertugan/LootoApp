package com.lotto.lottoapp.ui.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lotto.lottoapp.R
import com.lotto.lottoapp.core.components.BackgroundImage
import com.lotto.lottoapp.navigation.BottomBarNavigation

@Composable
fun LoginRegisterLayout(
    inputComponent: @Composable (navController: NavHostController) -> Unit,
    navController: NavHostController,
) {
    BackgroundImage()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        inputComponent(navController = navController)
        Box(modifier = Modifier.height(125.dp)) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(bottom = 75.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lotto_logo),
                    contentDescription = "logo",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(160.dp)
                        .height(50.dp)
                        .align(Alignment.BottomCenter)


                )
            }
        }
    }


}


@Composable
fun OtpScreenLayout(
    inputComponent: @Composable (navController: NavHostController) -> Unit,
    navController: NavHostController,
) {
    BackgroundImage()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        inputComponent(navController = navController)
        Box(modifier = Modifier.height(125.dp)) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(bottom = 75.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lotto_logo),
                    contentDescription = "logo",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(160.dp)
                        .height(50.dp)
                        .align(Alignment.BottomCenter)


                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GeneralLayout(
    profileComponent: @Composable (navController: NavHostController) -> Unit,
    inputComponent: @Composable (navController: NavHostController) -> Unit,
    navController: NavHostController
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {

        Scaffold(
            bottomBar = { BottomBarNavigation() }
        ) {
            BackgroundImage()
            inputComponent(navController = navController)
            Box(modifier = Modifier
                .height(125.dp)
                .fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lotto_logo),
                        contentDescription = "logo",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .width(160.dp)
                            .height(50.dp)


                    )
                    profileComponent(navController = navController)
                    // Icon(
                    //     imageVector = Icons.Default.AccountCircle,
                    //     modifier = Modifier
                    //         .width(50.dp)
                    //         .height(50.dp)
                    //         .padding(end = 20.dp),
                    //     contentDescription = "profile-logo"
                    // )
                }
            }
        }
    }
}


