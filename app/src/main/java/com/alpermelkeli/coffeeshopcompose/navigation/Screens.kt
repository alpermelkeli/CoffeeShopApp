package com.alpermelkeli.coffeeshopcompose.navigation


sealed class HomeScreen(val route: String) {
    object Home : HomeScreen("home")
    object Favourite : HomeScreen("favourite")
    object ShoppingCart : HomeScreen("shoppingCart")
    object Settings : HomeScreen("settings")
}
sealed class OtherScreens(val route: String){
    object CoffeeDetails: OtherScreens("details/{coffeeId}")
    object EditAddress:OtherScreens("editAddress")
}
