package com.alpermelkeli.coffeeshopcompose.util

import com.alpermelkeli.coffeeshopcompose.model.Coffee
import com.alpermelkeli.coffeeshopcompose.model.CoffeeCardItem

fun isEmailValid(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return emailRegex.matches(email)
}
fun isPasswordValid(password: String): Boolean {
    return password.length >= 8
}
fun calculateCost(userCartState: List<CoffeeCardItem>, coffeeMap: MutableMap<String, Coffee>): Double {
    var totalCost = 0.0
    userCartState.forEach { coffeeCardItem ->
        val coffee = coffeeMap[coffeeCardItem.coffeeId]
        if (coffee != null) {
            totalCost += coffee.price * coffeeCardItem.quantity
        }
    }
    return totalCost
}