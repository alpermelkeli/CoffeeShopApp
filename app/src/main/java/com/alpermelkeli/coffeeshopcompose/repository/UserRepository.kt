package com.alpermelkeli.coffeeshopcompose.repository

import androidx.compose.material3.darkColorScheme
import com.alpermelkeli.coffeeshopcompose.model.CoffeeCardItem
import com.alpermelkeli.coffeeshopcompose.model.Location
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(onSuccess:()->Unit,
              onFailure:(String)->Unit
              ,email:String,password: String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(task.exception.toString())
                }
            }

    }

    fun getUserCart(
        onSuccess: (List<CoffeeCardItem>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firestore.collection("User")
            .document(getUserId().toString())
            .collection("items")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val coffeeCardItemList = mutableListOf<CoffeeCardItem>()
                    task.result.documents.forEach { document ->
                        val quantity = document.getLong("quantity")?.toInt() ?: 0
                        coffeeCardItemList.add(CoffeeCardItem(coffeeId = document.id, quantity = quantity))
                    }
                    onSuccess(coffeeCardItemList)
                } else {
                    onFailure("Error fetching cart: ${task.exception?.message}")
                }
            }
            .addOnFailureListener { e ->
                onFailure("Error fetching cart: $e")
            }
    }

    fun addFavourite(onSuccess: () -> Unit, onFailure: (String) -> Unit,
                     coffeeID: String) {

        firestore.collection("User")
            .document(getUserId().toString())
            .update("favourites", FieldValue.arrayUnion(coffeeID))
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure("Error adding favourite: $e")
            }
    }
    fun getFavourites(onSuccess: (List<String>) -> Unit, onFailure: (String) -> Unit) {
        firestore.collection("User")
            .document(getUserId().toString())
            .get()
            .addOnSuccessListener { document ->
                val favourites = document.get("favourites") as? List<String>
                if (favourites != null) {
                    onSuccess(favourites)
                } else {
                    onSuccess(emptyList())
                }
            }
            .addOnFailureListener { e ->
                onFailure("Error getting favourites: $e")
            }
    }

    fun addCart(
        coffeeId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val itemsCollection = firestore.collection("User")
            .document(getUserId().toString())
            .collection("items")

        itemsCollection.document(coffeeId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Document exists, update the quantity
                    val currentQuantity = document.getLong("quantity") ?: 0
                    itemsCollection.document(coffeeId)
                        .update("quantity", currentQuantity + 1)
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            onFailure("Error updating cart: $e")
                        }
                } else {
                    // Document does not exist, create a new one
                    val newCartItem = hashMapOf(
                        "quantity" to 1
                    )
                    itemsCollection.document(coffeeId)
                        .set(newCartItem)
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            onFailure("Error adding to cart: $e")
                        }
                }
            }
            .addOnFailureListener { e ->
                onFailure("Error fetching cart item: $e")
            }
    }
    fun decreaseCart(onSuccess: () -> Unit,
                     onFailure: (String) -> Unit,
                     coffeeId: String){
        val itemsCollection = firestore.collection("User")
            .document(getUserId().toString())
            .collection("items")

        itemsCollection.document(coffeeId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Document exists, update the quantity
                    val currentQuantity = document.getLong("quantity") ?: 0
                    if(currentQuantity.toInt()==1){
                        itemsCollection.document(coffeeId)
                            .delete()
                            .addOnSuccessListener { onSuccess() }}
                    else {
                        itemsCollection.document(coffeeId)
                            .update("quantity", currentQuantity - 1)
                            .addOnSuccessListener {
                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                onFailure("Error updating cart: $e")
                            }
                    }
                }
            }
    }
    fun removeCart(onSuccess: () -> Unit,
                   onFailure: (String) -> Unit,
                   coffeeId: String){
        val itemsCollection = firestore.collection("User")
            .document(getUserId().toString())
            .collection("items")

        itemsCollection.document(coffeeId)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener{onFailure("Fail ${it.message}")}
    }
    fun getAllLocations(onSuccess: (List<Location>) -> Unit,
                        onFailure: (String) -> Unit){
        val userId = getUserId().toString()
        val locationList : MutableList<Location> = mutableListOf()
        firestore.collection("User")
            .document(userId)
            .collection("locations")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    it.result.documents.forEach {
                        val document = it
                        val description = document.getString("description")
                        val latitude = document.getDouble("latitude")
                        val longitude = document.getDouble("longitude")
                        if (description != null && latitude != null && longitude != null) {
                            val location = Location(description, longitude, latitude)
                            locationList.add(location)
                        } else {
                            onFailure("Location data is incomplete.")
                        }
                        onSuccess(locationList)
                    }
                }
                else{
                    onFailure(it.exception?.message.toString())
                }

            }
            .addOnFailureListener{ onFailure(it.message.toString())}

    }

    fun getCurrentLocation(onSuccess: (Location) -> Unit,
                           onFailure: (String) -> Unit) {
        val userId = getUserId().toString()

        firestore.collection("User")
            .document(userId)
            .get()
            .addOnSuccessListener { userDocument ->
                val selectedLocationId = userDocument.getString("selectedLocation")
                if (selectedLocationId != null) {
                    fetchLocation(userId, selectedLocationId, onSuccess, onFailure)
                } else {
                    onFailure("selectedLocation is null.")
                }
            }
            .addOnFailureListener { e ->
                onFailure(e.message.toString())
            }
    }

    private fun fetchLocation(userId: String,
                              locationId: String,
                              onSuccess: (Location) -> Unit,
                              onFailure: (String) -> Unit) {
        firestore.collection("User")
            .document(userId)
            .collection("locations")
            .document(locationId)
            .get()
            .addOnSuccessListener { locationDocument ->
                val description = locationDocument.getString("description")
                val latitude = locationDocument.getDouble("latitude")
                val longitude = locationDocument.getDouble("longitude")

                if (description != null && latitude != null && longitude != null) {
                    val location = Location(description, longitude, latitude)
                    onSuccess(location)
                } else {
                    onFailure("Location data is incomplete.")
                }
            }
            .addOnFailureListener { e ->
                onFailure(e.message.toString())
            }
    }



    private fun getUserId(): String? {
        return auth.currentUser?.uid
    }

    fun isLogged(): Boolean {
        return auth.currentUser!=null
    }


}
