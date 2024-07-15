package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown80
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50

@Composable
fun TextFieldValidation(
    type: String,
    leadingIconVector: ImageVector,
    text: String,
    onValueChange: (String) -> Unit,
    isValid: MutableState<Boolean>,
    isPrivate:Boolean
) {

    TextField(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(60.dp),
        value = text,
        shape = ShapeDefaults.Medium,
        colors = TextFieldDefaults.colors().copy(unfocusedIndicatorColor = Color.Transparent,focusedIndicatorColor = Brown80,focusedLeadingIconColor = Brown80,cursorColor = Brown80,unfocusedContainerColor = White50, focusedContainerColor = White50, unfocusedLabelColor = Color.Black, focusedLabelColor = Brown80, focusedTrailingIconColor = Brown80),
        textStyle = TextStyle.Default.copy(color = Color.Black, fontSize = 12.sp),
        visualTransformation = if(isPrivate) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(text = type, fontSize = 12.sp, fontFamily = FontFamily.SansSerif) },
        trailingIcon = {
            if (isValid.value) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            } else {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        },
        leadingIcon = { Icon(imageVector = leadingIconVector, contentDescription = null) }
    )
}
@Preview(showBackground = true)
@Composable
fun preview(){
    Column(modifier = Modifier.fillMaxSize()) {

    }
    val isValid = remember{ mutableStateOf(false) }
    TextFieldValidation(
        type = "E-mail",
        leadingIconVector = Icons.Default.Email,
        text = "E-mail",
        onValueChange = {},
        isValid = isValid,
        isPrivate = false
    )

}