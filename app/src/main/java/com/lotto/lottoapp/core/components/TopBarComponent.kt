package com.lotto.lottoapp.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lotto.lottoapp.R
import com.lotto.lottoapp.navigation.Paths
import com.lotto.lottoapp.ui.feature.profile.ProfileScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable

fun TopBarComponent(
    navController: NavHostController,
) {

    val profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
    val balance = profileScreenViewModel.balanceState.collectAsState()



    SideEffect {
        CoroutineScope(Dispatchers.Main).launch {
            profileScreenViewModel.getBalance()
        }
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.lotto_logo),
            contentDescription = "logo",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(160.dp)
                .height(50.dp)
        )
        Row(
            modifier = Modifier.clickable { navController.navigate(Paths.PROFILE_SCREEN) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                tint = Color.White,
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp),
                contentDescription = "profile-logo"
            )
            Text(
                text = "${balance.value.amount} cr.",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
        }


    }
}