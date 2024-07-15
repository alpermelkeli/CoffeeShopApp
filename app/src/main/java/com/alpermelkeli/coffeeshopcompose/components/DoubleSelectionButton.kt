package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpermelkeli.coffeeshopcompose.ui.theme.Black90
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown80
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50

@Composable
fun DoubleSelectionButton(isDeliver:MutableState<Boolean>) {

    Row(modifier = Modifier.fillMaxWidth(0.8f).height(40.dp).background(White50, shape = ShapeDefaults.Medium).padding(3.dp)) {
        SelectionButton(
            onClick = { isDeliver.value = true },
            modifier = Modifier.weight(1f),
            text = "Deliver",
            isSelected = isDeliver.value
        )
        SelectionButton(
            onClick = { isDeliver.value = false },
            modifier = Modifier.weight(1f),
            text = "Pick Up",
            isSelected = !isDeliver.value
        )
    }
}

@Composable
fun SelectionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = ShapeDefaults.Small,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Brown80 else White50,
            contentColor = if(isSelected) White50 else Black90
        )
    ) {
        Text(text)
    }
}


