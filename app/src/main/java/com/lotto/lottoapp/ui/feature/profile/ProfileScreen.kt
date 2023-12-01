package com.lotto.lottoapp.ui.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lotto.lottoapp.ui.constants.Buttons
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.ui.theme.CustomGray
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.CustomPurpleV2
import com.lotto.lottoapp.ui.theme.Typography


@Composable
fun ProfileScreen() {

    val profileViewModel: ProfileScreenViewModel = hiltViewModel()

    val selectableAmounts = profileViewModel.selectableAmounts.collectAsState()
    val selectedAmount = profileViewModel.selectedAmount.collectAsState()

    val profileTitles = arrayOf(
        Constants.NAME,
        Constants.SURNAME,
        Constants.EMAIL,
        Constants.PHONE,
        Constants.CITY,
        Constants.BIRTHDATE
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {


                selectableAmounts.value.map { selectableAmountState ->
                    val selectedAmountColor =
                        if (selectableAmountState.title === selectedAmount.value.title) CustomPurpleV2 else CustomGray

                    Text(
                        text = selectableAmountState.title,
                        style = Typography.titleMedium.copy(color = Color.White),
                        modifier = Modifier
                            .clickable {
                                profileViewModel.updateBalance(
                                    state = selectableAmountState
                                )
                            }
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = selectedAmountColor)
                            .padding(12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Row {

                Text(
                    text = Buttons.SUBMIT_BTN,
                    style = Typography.titleMedium.copy(color = Color.White),
                    modifier = Modifier
                        .clickable {
                            profileViewModel.addBalance(selectedAmount.value.amount)
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = CustomPurple)
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        Column() {
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
                            //TODO
                        },
                    text = Buttons.EDIT_BTN,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                profileTitles.map {
                    Row {
                        Text(
                            text = "$it: ",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color(0xFFFFFFFF),
                            )
                        )
                        Text(
                            text = "John",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color(0xFFFFFFFF),
                            )
                        )
                    }
                }

            }
        }
    }
}