package com.alpermelkeli.coffeeshopcompose.model

data class User(
    val email:String,
    val password:String,
    val card:List<CoffeeCardItem>
)

