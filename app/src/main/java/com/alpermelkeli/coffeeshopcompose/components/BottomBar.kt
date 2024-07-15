package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.alpermelkeli.coffeeshopcompose.navigation.HomeScreen

@Composable
fun BottomBar(navController: NavHostController, shoppingCartCount: String) {

    val items = listOf(
        HomeScreen.Home to Icons.Default.Home,
        HomeScreen.Favourite to Icons.Default.Favorite,
        HomeScreen.ShoppingCart to Icons.Default.ShoppingCart,
        HomeScreen.Settings to Icons.Default.Settings
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items.forEach { (screen, icon) ->
            NavigationBarItem(
                icon = {
                    if (screen.route != HomeScreen.ShoppingCart.route) {
                        Icon(imageVector = icon, contentDescription = screen.route)
                    } else {
                        IconWithNotification(text = shoppingCartCount, icon = Icons.Default.ShoppingCart)
                    }
                },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                        }
                    }
                },
            )
        }
    }
}

