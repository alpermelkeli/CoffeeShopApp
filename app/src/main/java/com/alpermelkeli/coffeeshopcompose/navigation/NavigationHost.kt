package com.alpermelkeli.coffeeshopcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alpermelkeli.coffeeshopcompose.fragments.CoffeeDetailsScreenComposable
import com.alpermelkeli.coffeeshopcompose.fragments.EditAddressScreen
import com.alpermelkeli.coffeeshopcompose.fragments.FavouriteScreenComposable
import com.alpermelkeli.coffeeshopcompose.fragments.HomeScreenComposable
import com.alpermelkeli.coffeeshopcompose.fragments.SettingsScreenComposable
import com.alpermelkeli.coffeeshopcompose.fragments.ShoppingCartComposable
import com.alpermelkeli.coffeeshopcompose.viewmodel.CoffeeViewModel
import com.alpermelkeli.coffeeshopcompose.viewmodel.UserViewModel

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier, userViewModel: UserViewModel, coffeeViewModel: CoffeeViewModel) {
    NavHost(navController = navController, startDestination = HomeScreen.Home.route, modifier = modifier) {
        composable(HomeScreen.Home.route) { HomeScreenComposable(navController = navController, coffeeViewModel = coffeeViewModel, userViewModel = userViewModel) }
        composable(HomeScreen.Favourite.route) { FavouriteScreenComposable(userViewModel = userViewModel, coffeeViewModel = coffeeViewModel) }
        composable(HomeScreen.ShoppingCart.route) { ShoppingCartComposable(navController = navController,userViewModel = userViewModel, coffeeViewModel= coffeeViewModel) }
        composable(HomeScreen.Settings.route) { SettingsScreenComposable() }
        composable(OtherScreens.CoffeeDetails.route) { backStackEntry ->
            val coffeeId = backStackEntry.arguments?.getString("coffeeId")
            coffeeId?.let { CoffeeDetailsScreenComposable(it, coffeeViewModel, userViewModel) }
        }
        composable(OtherScreens.EditAddress.route){EditAddressScreen(userViewModel)}
    }
}