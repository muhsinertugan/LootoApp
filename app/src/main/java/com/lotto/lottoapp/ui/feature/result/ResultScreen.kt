package com.lotto.lottoapp.ui.feature.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.lotto.lottoapp.core.components.CustomInputField
import com.lotto.lottoapp.ui.constants.Buttons
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography

@Composable
fun ResultScreen() {


    val resultsTitle = arrayOf("Date", "Numbers", "Result")
    val isTicketSearched by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isTicketSearched) {
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "123456",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "Hit 6 number",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "You earn",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = " 1.000.000 \$",
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "Wanna check again?",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomInputField(
                fieldName = "",
                placeholderText = "XX-XX-XX-XX-XXXXX",
                text = "",
                onFieldValueChange = {},
                isError = false
            )
            Spacer(modifier = Modifier.padding(vertical = 40.dp))
            Text(
                text = Buttons.SUBMIT_BTN,
                style = Typography.titleMedium.copy(color = Color.White),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = CustomPurple)
                    .padding(vertical = 16.dp, horizontal = 36.dp)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        if (!isTicketSearched) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .zIndex(-1f)
                        .background(color = Color.White)
                        .width(120.dp)
                        .height(1.dp)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "Your Tickets",
                    style = Typography.titleLarge.copy(color = Color.White),
                    textAlign = TextAlign.Center,
                )
                Box(
                    modifier = Modifier
                        .zIndex(-1f)
                        .background(color = Color.White)
                        .width(120.dp)
                        .height(1.dp)
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                resultsTitle.map { title ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            style = TextStyle(
                                fontSize = 20.sp, fontWeight = FontWeight(600)
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,

                            )
                    }
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color.Transparent)
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Remains: 6d 2w 1h",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color.Red)
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Lost",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF169F00))
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Prize 100.000 \$",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF169F00))
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Prize 100.000 \$",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF169F00))
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Prize 100.000 \$",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF169F00))
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Prize 100.000 \$",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF169F00))
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Prize 100.000 \$",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF169F00))
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Prize 100.000 \$",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF169F00))
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Prize 100.000 \$",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF169F00))
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Prize 100.000 \$",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF169F00))
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Prize 100.000 \$",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "25/10/2023", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "123456", style = MaterialTheme.typography.titleSmall,

                            color = Color.White, textAlign = TextAlign.Center

                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF169F00))
                            .padding(vertical = 4.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(

                            text = "Prize 100.000 \$",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center

                        )
                    }
                }
            }
        }
    }

}

