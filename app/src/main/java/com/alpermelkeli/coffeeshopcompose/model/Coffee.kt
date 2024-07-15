package com.alpermelkeli.coffeeshopcompose.model

data class Coffee(
    val id:String,
    val imageUri:String,
    val name:String,
    val type:String,
    val rating:Double,
    val price:Double,
    val description:String
)
