package com.lotto.lottoapp.ui.feature.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lotto.lottoapp.navigation.Paths
import com.lotto.lottoapp.ui.constants.Buttons
import kotlinx.coroutines.launch


@Composable
fun ProfileEditButton(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.CenterEnd)
            .padding(vertical = 36.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "",
            tint = Color.White
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(Paths.EDIT_PROFILE_SCREEN)
                    }

                },
            text = Buttons.EDIT_BTN,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFFFFFFFF),
            )
        )
    }
}