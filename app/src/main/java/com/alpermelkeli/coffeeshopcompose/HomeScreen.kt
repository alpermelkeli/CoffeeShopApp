package com.alpermelkeli.coffeeshopcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alpermelkeli.coffeeshopcompose.components.BottomBar
import com.alpermelkeli.coffeeshopcompose.navigation.NavigationHost
import com.alpermelkeli.coffeeshopcompose.navigation.HomeScreen
import com.alpermelkeli.coffeeshopcompose.navigation.OtherScreens
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown80
import com.alpermelkeli.coffeeshopcompose.ui.theme.CoffeeShopComposeTheme
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50
import com.alpermelkeli.coffeeshopcompose.viewmodel.CoffeeViewModel
import com.alpermelkeli.coffeeshopcompose.viewmodel.UserViewModel

class HomeScreen : ComponentActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var coffeeViewModel: CoffeeViewModel
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = UserViewModel()
        coffeeViewModel = CoffeeViewModel()
        setContent {
            CoffeeShopComposeTheme {
                val navController = rememberNavController()
                MainScreen(navController = navController, userViewModel = userViewModel, coffeeViewModel = coffeeViewModel)
            }
        }
    }
}
@Composable
fun MainScreen(navController: NavHostController, userViewModel: UserViewModel, coffeeViewModel: CoffeeViewModel) {

    userViewModel.getUserCarts()

    var currentRoute by remember { mutableStateOf<String?>(null) }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentRoute = destination.route
    }

    val userCartListState by userViewModel.userCart.observeAsState(emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            /**
             * If current route not in HomeScreen don't show BottomAppBar.(Ex. coffee details activity)
             */
            val showBottomBar = currentRoute !in listOf(OtherScreens.CoffeeDetails.route, OtherScreens.EditAddress.route)

            if (showBottomBar) {
                BottomAppBar(
                    modifier = Modifier
                        .border(BorderStroke(1.dp, White50), shape = MaterialTheme.shapes.medium)
                        .fillMaxHeight(0.1f),
                    containerColor = Color.White,
                    contentColor = Brown80
                ) {
                    BottomBar(navController = navController, shoppingCartCount = userCartListState.size.toString())
                }
            }
        }
    ) { innerPadding ->
        NavigationHost(navController = navController, modifier = Modifier.padding(innerPadding), userViewModel = userViewModel, coffeeViewModel = coffeeViewModel)
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    CoffeeShopComposeTheme {
        val navController = rememberNavController()
    }
}
