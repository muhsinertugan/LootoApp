package com.lotto.lottoapp.ui.feature.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.lotto.lottoapp.core.components.CustomInputField
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography

@Composable
fun ResultScreen(){
Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
    Spacer(modifier = Modifier.padding(vertical = 40.dp))
    CustomInputField(
        fieldName = "",
        placeholderText = "XX-XX-XX-XX-XXXXX",
        text = "",
        onFieldValueChange = {},
        isError = false
    )
    Spacer(modifier = Modifier.padding(vertical = 40.dp))
    Text(
        text = "Submit",
        style = Typography.titleMedium.copy(color = Color.White),
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = CustomPurple)
            .padding(vertical = 16.dp, horizontal = 36.dp)
    )
}
    Spacer(modifier = Modifier.padding(vertical = 20.dp))

Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
    Box(modifier = Modifier.zIndex(-1f).background(color = Color.White).width(120.dp).height(1.dp))
    Text(
        modifier = Modifier.padding(horizontal = 10.dp),
        text = "Your Tickets",
        style = Typography.titleLarge.copy(color = Color.White),
        textAlign = TextAlign.Center,
    )
    Box(modifier = Modifier.zIndex(-1f).background(color = Color.White).width(120.dp).height(1.dp))
}

}