package com.lotto.lottoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lotto.lottoapp.components.BackgroundImage
import com.lotto.lottoapp.screens.Navigation
import com.lotto.lottoapp.ui.theme.LottoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottoAppTheme {
                // A surface container using the 'background' color from the theme
                BackgroundImage()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    Navigation()

                }
            }
        }
    }
}

