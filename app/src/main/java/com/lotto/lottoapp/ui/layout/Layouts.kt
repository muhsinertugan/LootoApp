package com.lotto.lottoapp.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lotto.lottoapp.R
import com.lotto.lottoapp.core.components.BackgroundImage
import com.lotto.lottoapp.core.components.TopBarComponent
import com.lotto.lottoapp.navigation.bottomNavigation.BottomBarNavigation

@Composable
fun LoginRegisterLayout(
    inputComponent: @Composable () -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
) {

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            ) {
                Snackbar(snackbarData = it, containerColor = Color.Red, contentColor = Color.White)
            }
        }
    ) { innerPadding ->
        BackgroundImage()
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                inputComponent()
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
    }


}


@Composable
fun OtpScreenLayout(
    inputComponent: @Composable (navController: NavHostController) -> Unit,
    navController: NavHostController,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

    ) { innerPadding ->
        BackgroundImage()
        Column(modifier = Modifier.padding(innerPadding)) {

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

    }


}

@Composable
fun GeneralLayout(
    inputComponent: @Composable (snackbarHost: SnackbarHostState) -> Unit,
    navController: NavHostController,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {

        Scaffold(
            bottomBar = { BottomBarNavigation(navController = navController) },
            topBar = { TopBarComponent(navController = navController) },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { innerPadding ->
            BackgroundImage()
            Column(modifier = Modifier.padding(innerPadding)) {
                inputComponent(snackbarHostState)

            }

        }
    }
}


