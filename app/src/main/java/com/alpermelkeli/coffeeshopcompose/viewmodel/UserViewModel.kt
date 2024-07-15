package com.alpermelkeli.coffeeshopcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alpermelkeli.coffeeshopcompose.model.CoffeeCardItem
import com.alpermelkeli.coffeeshopcompose.model.Location
import com.alpermelkeli.coffeeshopcompose.repository.UserRepository

class UserViewModel : ViewModel() {

    private val userRepository: UserRepository = UserRepository()

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    private val _error = MutableLiveData<String?>()
    val error: MutableLiveData<String?> get() = _error

    private val _userCart = MutableLiveData<List<CoffeeCardItem>>()
    val userCart : LiveData<List<CoffeeCardItem>> get() = _userCart

    private val _userFavourites = MutableLiveData<List<String>>()
    val userFavourites : LiveData<List<String>> get() = _userFavourites

    private val _currentLocation = MutableLiveData<Location>()
    val currentLocation : LiveData<Location> get() = _currentLocation

    private val _allLocations = MutableLiveData<List<Location>>()
    val allLocations : LiveData<List<Location>> get() = _allLocations

    fun isLogged(): Boolean {
        return userRepository.isLogged()
    }

    fun login(email: String, password: String) {
        userRepository.login(
            onSuccess = {
                _loginResult.value = true
                _error.value = null
            },
            onFailure = {
                _error.value = null
                _error.value = it },
            email = email,
            password = password
            )
    }

    fun getUserCarts(){
        userRepository.getUserCart(
            onSuccess = { coffeeCardItems ->
                _userCart.postValue(coffeeCardItems)
            },
            onFailure = {_error.value = it}
            )
    }
    fun addCart(coffeeId:String){
        userRepository.addCart(
            coffeeId = coffeeId,
            onSuccess = { getUserCarts() },
            onFailure = { println(it) }
            )
    }
    fun decreaseCart(coffeeId: String){
        userRepository.decreaseCart(

            onSuccess = {getUserCarts()},
            onFailure = {},
            coffeeId = coffeeId
        )
    }
    fun removeCart(coffeeId: String){
        userRepository.removeCart(
            onSuccess = {getUserCarts()},
            onFailure = {},
            coffeeId = coffeeId
        )
    }
    fun addFavourite(coffeeId: String){
        userRepository.addFavourite(
            onSuccess = {getFavourites()},
            onFailure = {},
            coffeeID = coffeeId
            )
    }

    fun getFavourites(){
        userRepository.getFavourites(
            onSuccess = {_userFavourites.value = it},
            onFailure = {_error.value = it}
        )
    }
    fun getCurrentLocation(){
        userRepository.getCurrentLocation(
            onSuccess = {_currentLocation.value = it},
            onFailure = {_error.value = it}
        )
    }
    fun getAllLocations(){
        userRepository.getAllLocations(
            onSuccess = {_allLocations.value = it},
            onFailure = {_error.value = it}
        )
    }
}
