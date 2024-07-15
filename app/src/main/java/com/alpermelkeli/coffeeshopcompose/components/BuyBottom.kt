package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown80
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50

@Composable
fun BuyBottom(price:String, onBuyClicked:()->Unit){

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .background(
            Color.White,
            shape = ShapeDefaults.Large.copy(
                bottomStart = CornerSize(0.dp),
                bottomEnd = CornerSize(0.dp)
            )
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        ) {
        Spacer(modifier = Modifier.width(20.dp))

        Column(modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            ,
            horizontalAlignment = Alignment.Start) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Price",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
                )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "$ $price",
                color = Brown80
                )

        }
        NextButton(onClick = { onBuyClicked() }, modifier = Modifier
            .weight(2f)
            .fillMaxHeight(0.5f), text = "Buy Now", enabled = true)
        Spacer(modifier = Modifier.width(20.dp))

    }


}
