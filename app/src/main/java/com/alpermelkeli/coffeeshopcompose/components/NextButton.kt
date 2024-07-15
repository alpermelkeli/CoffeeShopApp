package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown80
import com.alpermelkeli.coffeeshopcompose.ui.theme.soraFontFamily

@Composable
fun NextButton(onClick:() -> Unit, modifier: Modifier, text:String, enabled:Boolean) {
    Button(onClick = onClick,
        modifier = modifier,
        colors = ButtonColors(containerColor = Brown80,
            contentColor = Color.White,
            disabledContentColor = Brown80,
            disabledContainerColor = Color.White),
        shape = ShapeDefaults.Medium,
        enabled = enabled
    )
    {
        Text(text = text,
            textAlign = TextAlign.Center,
            fontFamily = soraFontFamily
            )
    }

}

@Preview(showBackground = true)
@Composable
fun ButtonPreview(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NextButton(onClick = {println("tıkladı")},
            Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            "Get Started",true
            )
    }
}