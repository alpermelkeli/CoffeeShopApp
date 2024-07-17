package com.alpermelkeli.coffeeshopcompose.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alpermelkeli.coffeeshopcompose.components.CoffeeOrderItem
import com.alpermelkeli.coffeeshopcompose.components.DoubleSelectionButton
import com.alpermelkeli.coffeeshopcompose.components.NextButton
import com.alpermelkeli.coffeeshopcompose.model.Coffee
import com.alpermelkeli.coffeeshopcompose.model.CoffeeCardItem
import com.alpermelkeli.coffeeshopcompose.navigation.OtherScreens
import com.alpermelkeli.coffeeshopcompose.repository.CoffeeRepository
import com.alpermelkeli.coffeeshopcompose.util.calculateCost
import com.alpermelkeli.coffeeshopcompose.viewmodel.CoffeeViewModel
import com.alpermelkeli.coffeeshopcompose.viewmodel.UserViewModel

@Composable
fun ShoppingCartComposable(navController: NavController,userViewModel: UserViewModel,coffeeViewModel: CoffeeViewModel) {
    val coffeeMap = remember{ mutableStateMapOf<String, Coffee>() }
    val userCartsState by userViewModel.userCart.observeAsState(emptyList())
    val allCoffee = coffeeViewModel.allCoffees.observeAsState(emptyList())
    val locationState by userViewModel.currentLocation.observeAsState(null)
    LaunchedEffect(Unit) {
        userViewModel.getCurrentLocation()
        userViewModel.getUserCarts()
        allCoffee.value.forEach {
            coffee-> coffeeMap[coffee.id] = coffee
        }
    }

    val isDeliver = remember{ mutableStateOf(true) }

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                DoubleSelectionButton(isDeliver)
            }

            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Delivery Address",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 15.dp))
            Spacer(modifier = Modifier.height(13.dp))
            Text(text = locationState?.description.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Kpg. Sutoyo No.620, Bilzen, Tanjungbalai",
                fontSize = 13.sp,
                fontWeight = FontWeight.ExtraLight,
                modifier = Modifier.padding(start = 15.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.padding(start = 15.dp)) {
                Button(onClick = { navController.navigate(OtherScreens.EditAddress.route) },
                    colors = ButtonDefaults.buttonColors().copy(containerColor = Color.White, contentColor = Color.Black),
                    border = ButtonDefaults.outlinedButtonBorder,
                    modifier = Modifier
                        .height(30.dp)
                        .align(Alignment.CenterVertically),
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    Text(text = "Edit Address",
                        fontSize = 10.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors().copy(containerColor = Color.White, contentColor = Color.Black),
                    border = ButtonDefaults.outlinedButtonBorder,
                    modifier = Modifier
                        .height(33.dp)
                        .align(Alignment.CenterVertically),
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
                    Text(text = "Add Note",
                        fontSize = 10.sp)
                }


            }
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(start = 15.dp, end = 15.dp)) {
                items(userCartsState) { coffeeCardItem ->
                    CoffeeOrderItem(
                        coffeeCardItem = coffeeCardItem,
                        onClickedIncreaseQuantity = {userViewModel.addCart(coffeeId = coffeeCardItem.coffeeId) },
                        onClickedDecreaseQuantity ={userViewModel.decreaseCart(coffeeId = coffeeCardItem.coffeeId) },
                        onClickedRemoveCartItem = {userViewModel.removeCart(coffeeId = coffeeCardItem.coffeeId) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Payment Summary",
                modifier = Modifier.padding(15.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Price",
                    modifier = Modifier.padding(start = 15.dp))

                Text(text = "$ " + calculateCost(userCartsState,coffeeMap).toString(),
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp))
            }
            Spacer(Modifier.height(5.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Delivery fee",
                    modifier = Modifier.padding(start = 15.dp))
                Text(text = "$ 1.0",
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                NextButton(onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterVertically), text = "Buy Order", enabled = true)
            }

        }
    }
}


@Preview
@Composable
fun Test(){
    val isDeliver = remember{ mutableStateOf(true) }
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(Color.White)
        ) {
            DoubleSelectionButton(isDeliver)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Delivery Address",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)
            Spacer(modifier = Modifier.height(13.dp))
            Text(text = "Jl. Kpg Suotoyo",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Kpg. Sutoyo No.620, Bilzen, Tanjungbalai",
                fontSize = 13.sp,
                fontWeight = FontWeight.ExtraLight)
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors().copy(containerColor = Color.White, contentColor = Color.Black),
                    border = ButtonDefaults.outlinedButtonBorder,
                    modifier = Modifier
                        .height(30.dp)
                        .align(Alignment.CenterVertically),
                    ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    Text(text = "Edit Address",
                        fontSize = 10.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors().copy(containerColor = Color.White, contentColor = Color.Black),
                    border = ButtonDefaults.outlinedButtonBorder,
                    modifier = Modifier
                        .height(33.dp)
                        .align(Alignment.CenterVertically),
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
                    Text(text = "Add Note",
                        fontSize = 10.sp)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .height(1.dp))

        }
    }
}