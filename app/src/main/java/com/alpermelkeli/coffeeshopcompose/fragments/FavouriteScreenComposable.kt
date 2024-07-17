package com.alpermelkeli.coffeeshopcompose.fragments

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.alpermelkeli.coffeeshopcompose.model.Coffee
import com.alpermelkeli.coffeeshopcompose.repository.CoffeeRepository
import com.alpermelkeli.coffeeshopcompose.viewmodel.CoffeeViewModel
import com.alpermelkeli.coffeeshopcompose.viewmodel.UserViewModel
import kotlinx.coroutines.launch


@Composable
fun FavouriteScreenComposable(userViewModel: UserViewModel, coffeeViewModel: CoffeeViewModel) {
    val coffeeRepository = CoffeeRepository()
    val favouriteListState by userViewModel.userFavourites.observeAsState(emptyList())

    val coffeeStates = remember(favouriteListState) {
        mutableStateListOf<CoffeeState>().apply {
            favouriteListState.forEach { add(CoffeeState(isLoading = true)) }
        }
    }

    val coroutineScope = rememberCoroutineScope()

    favouriteListState.forEachIndexed { index, favouriteItem ->
        LaunchedEffect(favouriteItem) {
            coroutineScope.launch {
                coffeeRepository.getCoffeeById(
                    onSuccess = { coffee ->
                        coffeeStates[index] = CoffeeState(coffee = coffee, isLoading = false)
                    },
                    onFailure = { error ->
                        coffeeStates[index] = CoffeeState(errorMessage = error, isLoading = false)
                    },
                    favouriteItem
                )
            }
        }
    }

    LazyColumn {
        items(coffeeStates) { coffeeState ->
            when {
                coffeeState.isLoading -> Text(text = "Loading...")
                coffeeState.errorMessage != null -> Text(text = "Error: ${coffeeState.errorMessage}")
                coffeeState.coffee != null -> Text(text = coffeeState.coffee.name)
            }
        }
    }
}

data class CoffeeState(
    val coffee: Coffee? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
