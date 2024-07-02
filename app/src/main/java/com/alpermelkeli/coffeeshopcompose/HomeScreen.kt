package com.alpermelkeli.coffeeshopcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alpermelkeli.coffeeshopcompose.components.fragments.FavouriteScreenComposable
import com.alpermelkeli.coffeeshopcompose.components.fragments.HomeScreenComposable
import com.alpermelkeli.coffeeshopcompose.components.fragments.ProfileScreenComposable
import com.alpermelkeli.coffeeshopcompose.components.fragments.SettingsScreenComposable
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown80
import com.alpermelkeli.coffeeshopcompose.ui.theme.CoffeeShopComposeTheme
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50

class HomeScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeShopComposeTheme {
                val navController = rememberNavController()
                MainScreen(navController = navController)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.border(BorderStroke(1.dp, White50), shape = MaterialTheme.shapes.medium),
                containerColor = Color.White,
                contentColor = Brown80
                ) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavigationHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        Screen.Home to Icons.Default.Home,
        Screen.Favourite to Icons.Default.Favorite,
        Screen.Profile to Icons.Default.Person,
        Screen.Settings to Icons.Default.Settings
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items.forEach { (screen, icon) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = screen.route, tint = Brown80) },
                selected = navController.currentDestination?.route == screen.route,
                onClick = { navController.navigate(screen.route) }
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) { HomeScreenComposable() }
        composable(Screen.Favourite.route) { FavouriteScreenComposable() }
        composable(Screen.Profile.route) { ProfileScreenComposable() }
        composable(Screen.Settings.route) { SettingsScreenComposable() }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favourite : Screen("favourite")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    CoffeeShopComposeTheme {
        val navController = rememberNavController()
        MainScreen(navController = navController)
    }
}
