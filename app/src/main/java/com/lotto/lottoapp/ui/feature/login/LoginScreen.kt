package com.lotto.lottoapp.ui.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lotto.lottoapp.core.components.CustomInputField
import com.lotto.lottoapp.ui.theme.Typography

@Composable
fun LoginScreen(navController: NavHostController) {

    val horizontalGradient = Brush.horizontalGradient(
        colors = listOf(  Color(204,0,255),Color(128, 1,255) ),

    )

    Column(
        
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(600.dp)
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        CustomInputField(fieldName = "Email", placeholderText = "Enter your email")


        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Send Code",
            style = Typography.titleMedium.copy(color = Color.White),
            modifier = Modifier
                .clickable(onClick = {navController.navigate("register_screen")})
                .clip(RoundedCornerShape(8.dp))
                .background(brush = horizontalGradient)
                .padding(vertical = 16.dp, horizontal = 30.dp)
        )
}

}




