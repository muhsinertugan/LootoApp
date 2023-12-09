package com.lotto.lottoapp.ui.feature.editProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lotto.lottoapp.core.components.CustomDatePicker
import com.lotto.lottoapp.core.components.CustomInputField
import com.lotto.lottoapp.ui.constants.Buttons
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.ui.constants.Placeholders
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography

@Composable
fun EditProfileScreen() {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(700.dp)
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {

        CustomInputField(
            fieldName = Constants.EMAIL,
            placeholderText = Placeholders.EMAIL_PLACEHOLDER,
            text = "",
            onFieldValueChange = {},
            isError = false
        )
        CustomInputField(
            fieldName = Constants.NAME,
            placeholderText = Placeholders.NAME_PLACEHOLDER,
            text = "",
            onFieldValueChange = {
            },
            isError = false
        )
        CustomInputField(
            fieldName = Constants.SURNAME,
            placeholderText = Placeholders.SURNAME_PLACEHOLDER,
            text = "",
            onFieldValueChange = {

            },
            isError = false
        )
        CustomInputField(
            fieldName = Constants.PHONE,
            placeholderText = Placeholders.PHONE_PLACEHOLDER,
            text = "",
            onFieldValueChange = {
            },
            isError = false
        )

        CustomDatePicker(
            fieldName = Constants.BIRTHDATE,
            placeholderText = Placeholders.BIRTHDATE_PLACEHOLDER,
            text = ""
        ) {
        }


        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = Buttons.SUBMIT_BTN,
            style = Typography.titleMedium.copy(color = Color.White),
            modifier = Modifier
                .clickable {

                }
                .clip(RoundedCornerShape(8.dp))
                .background(color = CustomPurple)
                .padding(vertical = 16.dp, horizontal = 136.dp)
        )


    }
}
