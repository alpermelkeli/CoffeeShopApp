package com.alpermelkeli.coffeeshopcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpermelkeli.coffeeshopcompose.components.NextButton
import com.alpermelkeli.coffeeshopcompose.ui.theme.CoffeeShopComposeTheme
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50
import com.alpermelkeli.coffeeshopcompose.ui.theme.soraFontFamily

class IntroducePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeShopComposeTheme {
                IntroducePageScreen{
                    intent  = Intent(this,HomeScreen::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun IntroducePageScreen(onNext:()->Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .paint(
            painterResource(id = R.drawable.coffee_background),
            contentScale = ContentScale.Fit,
            alignment = Alignment.TopCenter
        ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
        )

    {
        Text(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
            text = "Fall in Love with\nCoffee in Blissful\nDelight!",
            fontSize = 30.sp,
            fontFamily = soraFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp
        )

        Text(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
            text = "Welcome to our cozy coffee corner, where\nevery cup is a delightful for you.",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            lineHeight = 17.sp,
            fontFamily = soraFontFamily,
            color = White50
        )

        Spacer(modifier = Modifier.height(20.dp))

        NextButton(onClick = onNext,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(60.dp)
                .align(Alignment.CenterHorizontally),
            text = "Get Started")

        Spacer(modifier = Modifier.height(15.dp))
    }

}
