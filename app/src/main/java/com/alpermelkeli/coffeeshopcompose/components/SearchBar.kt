package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpermelkeli.coffeeshopcompose.ui.theme.Black90
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown80
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50
import com.google.firebase.annotations.concurrent.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWithFilter(backgroundColor:Color, hasBackground: Boolean){
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = if (hasBackground) Modifier.background(color = backgroundColor, shape = ShapeDefaults.Large.copy(topStart = CornerSize(0.dp), topEnd = CornerSize(0.dp), bottomEnd = CornerSize(20.dp), bottomStart = CornerSize(20.dp))).height(150.dp) else Modifier
    ) {
        SearchBar(
            modifier = Modifier.weight(0.6f).padding(7.dp),
            query = "Search coffee",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {},
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = White50)},
            colors = SearchBarDefaults.colors(containerColor = Color(0xFF2A2A2A),
                inputFieldColors = TextFieldDefaults.colors().copy(unfocusedTextColor = White50, focusedTextColor = White50)),
            shape = ShapeDefaults.Small
            ){}
        Button(onClick = {}, modifier = Modifier
            .height(50.dp)
            .width(50.dp)
            .padding(top = 5.dp, end = 7.dp, start = 3.dp)
            .weight(0.14f)
            ,
            shape = ShapeDefaults.Small,
            colors = ButtonDefaults.buttonColors().copy(containerColor = Brown80),
            contentPadding = PaddingValues(8.dp)
        )
        {
            Icon(imageVector = Icons.Default.Build, contentDescription = "Filter", tint = White50)
        }
    }

}
@Preview
@Composable
fun Preview(){
    SearchBarWithFilter(Black90.copy(alpha = 1f-0), true)
}