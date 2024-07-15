package com.alpermelkeli.coffeeshopcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpermelkeli.coffeeshopcompose.model.Coffee
import com.alpermelkeli.coffeeshopcompose.repository.CoffeeRepository

class CoffeeViewModel : ViewModel() {

    private val coffeeRepository: CoffeeRepository = CoffeeRepository()

    private val _allCoffees: MutableLiveData<List<Coffee>> = MutableLiveData()
    val allCoffees: LiveData<List<Coffee>> get() = _allCoffees

    private val _coffee:MutableLiveData<Coffee> = MutableLiveData()
    val coffee:LiveData<Coffee> get() = _coffee

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    fun fetchAllCoffees() {
        coffeeRepository.getAllCoffees(
            onSuccess = { coffees ->
                _allCoffees.value = coffees
            },
            onFailure = { errorMessage ->
                _error.value = errorMessage
            }
        )
    }

    fun getCoffee(id:String){
        coffeeRepository.getCoffeeById(
            onSuccess = {_coffee.value = it},
            onFailure = {_error.value = it},
            id = id
        )
    }

}
