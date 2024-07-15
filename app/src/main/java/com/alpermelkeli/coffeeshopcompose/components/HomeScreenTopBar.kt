package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.alpermelkeli.coffeeshopcompose.ui.theme.Black90
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Black90, titleContentColor = White50),
        title = {
            Column{
                Text(text = "Location",
                    fontSize = 12.sp
                )
                Text(
                    text = "Bilzen, Tanjungbalai",
                    fontSize = 16.sp
                )
            }
        }
    )
}