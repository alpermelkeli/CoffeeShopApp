package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpermelkeli.coffeeshopcompose.R

@Composable
fun Advertisement(id:Int, modifier: Modifier) {
    Image(
        painter = painterResource(id = id),
        modifier = modifier
           .clip(RoundedCornerShape(16.dp)),
        contentDescription = "advertisement",
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
    )
}

@Preview(showBackground = true)
@Composable
fun Pre() {
    Advertisement(id = R.drawable.advertisement, modifier = Modifier.width(300.dp).height(120.dp))
}
