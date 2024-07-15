package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailsTop(onFavouriteClicked:()->Unit, isFavouriteState:MutableState<Boolean>){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 25.dp, end = 25.dp)
        .height(100.dp)
        .background(Color.Transparent),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "back",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(40.dp)
        )
        Text(text = "Detail",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Icon(imageVector = if(isFavouriteState.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Like",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(32.dp)
                .clickable { onFavouriteClicked()

                }
        )


    }

}