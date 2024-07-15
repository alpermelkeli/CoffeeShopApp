package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.alpermelkeli.coffeeshopcompose.R
import com.alpermelkeli.coffeeshopcompose.model.Coffee
import com.alpermelkeli.coffeeshopcompose.model.CoffeeCardItem
import com.alpermelkeli.coffeeshopcompose.repository.CoffeeRepository
import com.alpermelkeli.coffeeshopcompose.viewmodel.CoffeeViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoffeeOrderItem(
    coffeeCardItem: CoffeeCardItem,
    onClickedIncreaseQuantity: (CoffeeCardItem) -> Unit,
    onClickedDecreaseQuantity: (CoffeeCardItem) -> Unit,
    onClickedRemoveCartItem: (CoffeeCardItem) -> Unit,
) {
    val coffeeRepository = CoffeeRepository()
    val coffee = remember { mutableStateOf<Coffee?>(null) }

    // Fetch coffee details when the composable is first composed or when the coffee ID changes
    LaunchedEffect(coffeeCardItem.coffeeId) {
        coffeeRepository.getCoffeeById(
            onSuccess = { coffee.value = it },
            onFailure = { println(it) },
            coffeeCardItem.coffeeId
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp)
            .background(Color.White, shape = ShapeDefaults.Medium),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 5.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White).padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(data = coffee.value?.imageUri),
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(60.dp)
                    ,
                contentDescription = "advertisement",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
            Column(modifier = Modifier.fillMaxHeight().width(100.dp),
                verticalArrangement = Arrangement.Center) {
                Text(
                    text = coffee.value?.name ?: "Loading...",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = coffee.value?.type ?: "Loading...",
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 12.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.width(140.dp)
            ) {
                IconButton(onClick = { onClickedDecreaseQuantity(coffeeCardItem) }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Decrease Order"
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))

                Text(text = coffeeCardItem.quantity.toString())

                Spacer(modifier = Modifier.width(10.dp))

                IconButton(onClick = { onClickedIncreaseQuantity(coffeeCardItem) }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Increase Order"
                    )
                }
            }
            IconButton(
                onClick = { onClickedRemoveCartItem(coffeeCardItem) },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Order"
                )
            }
        }
    }
}
