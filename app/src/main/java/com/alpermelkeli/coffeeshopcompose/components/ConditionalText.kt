package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50
import com.alpermelkeli.coffeeshopcompose.ui.theme.soraFontFamily

@Composable
fun ConditionalText(text:String){

    val minimumLineLength = 2

    var expandedState by remember { mutableStateOf(false) }
    var showReadMoreButtonState by remember { mutableStateOf(false) }
    val maxLines = if (expandedState) 200 else minimumLineLength

    Column(modifier = Modifier.padding(end = 5.dp)) {
        Text(
            text = text,
            textAlign = TextAlign.Start,
            fontSize = 15.sp,
            color = Color.Gray,
            fontFamily = soraFontFamily,
            fontWeight = FontWeight.Light,
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines,
            onTextLayout = { textLayoutResult: TextLayoutResult ->
                if (textLayoutResult.lineCount > minimumLineLength-1) {
                    if (textLayoutResult.isLineEllipsized(minimumLineLength-1)) showReadMoreButtonState = true
                }
            }
        )
        if (showReadMoreButtonState) {
            Text(
                text = if (expandedState) "Read Less" else "Read More",
                color = Color.Gray,
                fontFamily = soraFontFamily,
                textAlign = TextAlign.Start,
                modifier = Modifier.clickable {
                    expandedState = !expandedState
                },

                style = MaterialTheme.typography.bodySmall

            )
        }

    }
}