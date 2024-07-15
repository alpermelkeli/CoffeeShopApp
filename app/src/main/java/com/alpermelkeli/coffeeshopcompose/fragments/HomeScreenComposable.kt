package com.alpermelkeli.coffeeshopcompose.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alpermelkeli.coffeeshopcompose.R
import com.alpermelkeli.coffeeshopcompose.components.Advertisement
import com.alpermelkeli.coffeeshopcompose.components.CoffeeMenuItem
import com.alpermelkeli.coffeeshopcompose.components.HorizontalCoffeeTypeMenu
import com.alpermelkeli.coffeeshopcompose.components.SearchBarWithFilter
import com.alpermelkeli.coffeeshopcompose.ui.theme.Black90
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown50
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50
import com.alpermelkeli.coffeeshopcompose.viewmodel.CoffeeViewModel
import com.alpermelkeli.coffeeshopcompose.viewmodel.UserViewModel


@Composable
fun HomeScreenComposable(navController: NavHostController, coffeeViewModel: CoffeeViewModel, userViewModel: UserViewModel) {
    LaunchedEffect(Unit) {
        userViewModel.getFavourites()
        coffeeViewModel.fetchAllCoffees()
    }
    val userFavourites by userViewModel.userFavourites.observeAsState(emptyList())
    val coffeeListState by coffeeViewModel.allCoffees.observeAsState(emptyList())
    val scrollState = rememberLazyListState()
    val offset by remember {
        derivedStateOf { (scrollState.firstVisibleItemScrollOffset / 200f).coerceIn(0f, 1f) } // Normalize offset (0-1)
    }
    val coffeeTypes = listOf("All Coffees","Espresso", "Latte", "Cappuccino", "Mocha")
    val selectedCoffeeType = remember{ mutableStateOf("All Coffees") }
    val selectedMenuTabIndex = remember{ mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEAEAEA),
    ) {
        Box {


            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
                state = scrollState,
                modifier = Modifier.background(
                    brush =  Brush.linearGradient(0.3f to Brown50,
                        0.4f to White50)
                )
            )
            {

                item{


                    SearchBarWithFilter(Black90.copy(alpha = 1f-offset), true)

                    Advertisement(
                        id = R.drawable.advertisement,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(150.dp)
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                            .padding(top = 15.dp)
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    HorizontalCoffeeTypeMenu(selectedTabIndex = selectedMenuTabIndex,
                        coffeeTypes = coffeeTypes) {
                        selectedCoffeeType.value = it
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                }

                itemsIndexed(coffeeListState.chunked(2)) { _, rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        rowItems.forEach { coffee ->
                            if(selectedCoffeeType.value!="All Coffees"){
                                if(coffee.type==selectedCoffeeType.value)
                                    CoffeeMenuItem(coffee = coffee,
                                        onClickAddFavourite = {userViewModel.addFavourite(coffee.id)},
                                        onClickCard = { navController.navigate("details/${coffee.id}")},
                                        isFavourite = coffee.id in userFavourites)


                            }
                            else{
                                CoffeeMenuItem(coffee = coffee,
                                    onClickAddFavourite = {userViewModel.addFavourite(coffee.id) },
                                    onClickCard = { navController.navigate("details/${coffee.id}")},
                                    isFavourite = coffee.id in userFavourites)
                            }

                        }
                    }
                }

            }
            //if(offset>0.74f) SearchBarWithFilter(backgroundColor = Black90,false)

        }

    }
}




@Preview(showBackground = true)
@Composable
fun Preview() {
}