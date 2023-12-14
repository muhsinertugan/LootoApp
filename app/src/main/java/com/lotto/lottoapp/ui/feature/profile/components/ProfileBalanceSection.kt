package com.lotto.lottoapp.ui.feature.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lotto.lottoapp.ui.constants.Buttons
import com.lotto.lottoapp.ui.feature.profile.ProfileScreenContract
import com.lotto.lottoapp.ui.theme.CustomGray
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.CustomPurpleV2
import com.lotto.lottoapp.ui.theme.Typography

@Composable
fun ProfileBalanceSection(
    selectableAmounts: State<List<ProfileScreenContract.SelectableAmountState>>,
    selectedAmount: State<ProfileScreenContract.SelectableAmountState>,
    handleAddBalance: (Int) -> Unit,
    handleUpdateBalance: (ProfileScreenContract.SelectableAmountState) -> Unit
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
                            handleUpdateBalance(selectableAmountState)
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
                        handleAddBalance(selectedAmount.value.amount)
                    }
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = CustomPurple)
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .wrapContentSize(align = Alignment.Center)
            )
        }
    }
}