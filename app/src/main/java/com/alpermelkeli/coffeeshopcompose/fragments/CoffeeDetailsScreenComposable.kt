package com.alpermelkeli.coffeeshopcompose.fragments
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.alpermelkeli.coffeeshopcompose.components.DetailsTop
import com.alpermelkeli.coffeeshopcompose.viewmodel.CoffeeViewModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.alpermelkeli.coffeeshopcompose.R
import com.alpermelkeli.coffeeshopcompose.components.BuyBottom
import com.alpermelkeli.coffeeshopcompose.components.ConditionalText
import com.alpermelkeli.coffeeshopcompose.ui.theme.Black90
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown80
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50
import com.alpermelkeli.coffeeshopcompose.viewmodel.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoffeeDetailsScreenComposable(coffeeId: String, coffeeViewModel: CoffeeViewModel, userViewModel: UserViewModel) {

    coffeeViewModel.getCoffee(coffeeId)

    val scrollState = rememberLazyListState()

    val selectedCoffeeState = coffeeViewModel.coffee.observeAsState(null)

    val isFavouriteState = remember { mutableStateOf(false) }

    LaunchedEffect(selectedCoffeeState.value) {
        selectedCoffeeState.value?.id?.let { id ->
            isFavouriteState.value = userViewModel.userFavourites.value?.contains(id) == true
        }
    }

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(White50)) {
        Scaffold(
            topBar = {
                DetailsTop(
                    onFavouriteClicked = {
                        userViewModel.addFavourite(selectedCoffeeState.value?.id.toString())
                        isFavouriteState.value = true
                    },
                    isFavouriteState = (isFavouriteState)
                )
            },
        bottomBar = { BuyBottom(price = selectedCoffeeState.value?.price.toString()){
                userViewModel.addCart(
                    selectedCoffeeState.value?.id.toString()
                )
            }
            }){

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp, bottom = 110.dp),
                horizontalAlignment = Alignment.CenterHorizontally, contentPadding = PaddingValues(15.dp),
                state = scrollState
            ) {


                item {
                    if (selectedCoffeeState.value != null) {

                        Image(
                        painter = rememberImagePainter(data = selectedCoffeeState.value?.imageUri),
                        contentDescription = "CoffeePicture",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                            ,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop)
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)){
                        Text(text = selectedCoffeeState.value?.name.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.coffee_nucleus),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .background(White50, shape = ShapeDefaults.Medium),
                            tint = Brown80
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.closed_coffee),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .background(White50, shape = ShapeDefaults.Medium),
                            tint = Brown80
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.coffee_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .background(White50, shape = ShapeDefaults.Medium),
                            tint = Brown80
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Star ,contentDescription = "Star", tint = Color(color = 0xFFFDD835))
                        Text(text = selectedCoffeeState.value?.rating.toString(),
                            Modifier.padding(start = 3.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "(230)",
                            Modifier.padding(start = 3.dp),
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp
                        )


                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Spacer(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(Black90)
                        .height(0.5.dp)
                    )


                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)) {
                        Row(Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start) {
                            Text(text = "Description",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
                        ) {
                            ConditionalText(text = selectedCoffeeState.value?.description.toString()
                            )

                        }

                    }


                }

            }


        }
    }
}
