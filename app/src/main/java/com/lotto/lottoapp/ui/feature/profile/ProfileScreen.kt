package com.lotto.lottoapp.ui.feature.profile

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)

@Composable
fun ProfileScreen() {
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
                Text(
                    text = "10.000 cr",
                    style = Typography.titleMedium.copy(color = Color.White),
                    modifier = Modifier
                        .clickable {
                            //TODO
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = CustomPurple)
                        .padding(12.dp)
                )

                Text(
                    text = "20.000 cr",
                    style = Typography.titleMedium.copy(color = Color.White),
                    modifier = Modifier
                        .clickable {
                            //TODO
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = CustomPurple)
                        .padding(12.dp)

                )
                Text(
                    text = "30.000 cr",
                    style = Typography.titleMedium.copy(color = Color.White),
                    modifier = Modifier
                        .clickable {
                            //TODO
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = CustomPurple)
                        .padding(12.dp)

                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Row {

                Text(
                    text = "Send Code",
                    style = Typography.titleMedium.copy(color = Color.White),
                    modifier = Modifier
                        .clickable {
                            //TODO
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
                    text = "Edit Profile",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
            Column() {
                Text(
                    text = "Name: John ",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF),
                    )
                )
                Text(
                    text = "Surname:  Doe ",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF),
                    )
                )
                Text(
                    text = "E-mail: example@gmail.com",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF),
                    )
                )
                Text(
                    text = "Phone number: 905555555555555",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF),
                    )
                )
                Text(
                    text = "City: Zonguldak",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF),
                    )
                )
                Text(
                    text = "Birthday: 15/03/1994",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
        }
    }
}