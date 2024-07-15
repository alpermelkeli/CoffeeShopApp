package com.alpermelkeli.coffeeshopcompose.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown30
import com.alpermelkeli.coffeeshopcompose.ui.theme.Brown80
import com.alpermelkeli.coffeeshopcompose.ui.theme.White50

@Composable
fun HorizontalCoffeeTypeMenu(
    selectedTabIndex:MutableState<Int>,
    coffeeTypes: List<String>,
    onCoffeeTypeClick: (String) -> Unit
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(White50)

    ) {
        ScrollableTabRow(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            modifier = Modifier.background(Color.Transparent),
            selectedTabIndex = selectedTabIndex.value,
            edgePadding = 10.dp,
            indicator = {},
        ) {
            coffeeTypes.forEachIndexed { index, coffeeType ->
                Box(modifier = Modifier.wrapContentHeight().wrapContentWidth().padding(5.dp).background(Color.Transparent),
                    contentAlignment = Alignment.Center)
                {
                    Tab(
                        selectedContentColor = Brown80,
                        unselectedContentColor = Color.LightGray,
                        modifier = Modifier
                            .background(if(selectedTabIndex.value == index) Brown80 else Color.LightGray, shape = ShapeDefaults.Small)
                            .height(30.dp)
                            .wrapContentWidth(),
                        selected = selectedTabIndex.value == index,
                        onClick = {
                            selectedTabIndex.value = index
                            onCoffeeTypeClick(coffeeType)
                        },
                        text = {
                            Text(
                                text = coffeeType,
                                fontSize = 16.sp,
                                color = if(selectedTabIndex.value == index) Color.White else Color.DarkGray,
                                fontWeight = if (selectedTabIndex.value == index) FontWeight.Bold else FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    var selectedTabIndex = remember{ mutableStateOf(0) }
    val coffeeTypes = listOf("All Coffees","Espresso", "Latte", "Cappuccino", "Mocha")
    HorizontalCoffeeTypeMenu(selectedTabIndex,coffeeTypes) {
        // Handle coffee type click here (for preview, you can print a message)
        println("Coffee type clicked: $it")
    }
}
