package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun IconWithNotification(
    text: String,
    icon: ImageVector
) {
    BadgedBox(
        badge = {
            Badge(
                modifier = Modifier.offset(y=10.dp),
                containerColor= LightGray
            ){
                Text(
                    text,
                    modifier = Modifier.semantics {
                        contentDescription = "$text new notifications"
                    }
                )
            }
        }) {
        Icon(
            icon,
            contentDescription = "Favorite"
        )
    }
}