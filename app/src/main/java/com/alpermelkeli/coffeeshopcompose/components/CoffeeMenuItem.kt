package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.alpermelkeli.coffeeshopcompose.model.Coffee
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown80

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoffeeMenuItem(coffee: Coffee, onClickAddFavourite:(Coffee)->Unit, onClickCard:(Coffee)->Unit,
                   isFavourite:Boolean) {
    Card(
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
        modifier = Modifier
            .padding(15.dp)
            .width(150.dp)
            .height(220.dp)
            .clickable{onClickCard(coffee)},
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Box{

            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Image(
                    painter = rememberImagePainter(data = coffee.imageUri),
                    contentDescription = "CoffeePicture",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .clip(
                            ShapeDefaults.Large.copy(
                                topStart = CornerSize(0.dp),
                                topEnd = CornerSize(0.dp)
                            )
                        ),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Text(text = coffee.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 7.dp, top = 5.dp))

                    Text(text = coffee.type, fontSize = 10.sp, fontWeight = FontWeight.Light, modifier = Modifier.padding(start = 7.dp, top = 5.dp))

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {

                        Text(text = "$" + coffee.price.toString(), fontSize = 14.sp, modifier = Modifier.padding(start = 7.dp,top = 8.dp), fontWeight = FontWeight.Bold)

                        Button(
                            contentPadding = PaddingValues(5.dp),
                            onClick = { onClickAddFavourite(coffee) },
                            shape = ShapeDefaults.Small,
                            modifier = Modifier
                                .height(35.dp)
                                .width(35.dp)
                                .padding(end = 8.dp, bottom = 3.dp),
                            colors = ButtonDefaults.buttonColors().copy(containerColor = Brown80)
                            ) {
                            Icon(
                                imageVector = if(isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Add",
                                modifier = Modifier.size(20.dp),
                                tint = Color.White)

                            
                        }
                    }
                }
            }

            Icon(
                Icons.Filled.Star,
                tint = Color(color = 0xFFFDD835),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 30.dp, top = 5.dp)
                    .size(20.dp),
                contentDescription = "Rating"
            )
            Text(
                color = Color.White,
                text = coffee.rating.toString(),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 5.dp, top = 5.dp),
                fontSize = 14.sp,
            )
        }

    }
}
