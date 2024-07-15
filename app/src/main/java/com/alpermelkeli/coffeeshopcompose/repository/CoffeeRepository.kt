package com.alpermelkeli.coffeeshopcompose.repository

import com.alpermelkeli.coffeeshopcompose.model.Coffee
import com.google.firebase.firestore.FirebaseFirestore

class CoffeeRepository {

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getAllCoffees(
        onSuccess: (List<Coffee>) -> Unit,
        onFailure: (String) -> Unit)
    {
        firestore.collection("Coffee")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val coffeeList = mutableListOf<Coffee>()
                    task.result?.documents?.forEach { document ->
                        val imageUri = document.getString("imageUri") ?: ""
                        val name = document.getString("name") ?: ""
                        val type = document.getString("type") ?: ""
                        val rating = document.getDouble("rating") ?: 0.0
                        val price = document.getDouble("price") ?: 0.0
                        val description = document.getString("description") ?: ""

                        val coffee = Coffee(document.id, imageUri, name, type, rating, price, description)
                        coffeeList.add(coffee)
                    }
                    onSuccess(coffeeList)
                } else {
                    onFailure(task.exception?.message ?: "Bilinmeyen bir hata oluştu.")
                }
            }
    }

    fun getCoffeeById(onSuccess: (Coffee) -> Unit,
                      onFailure: (String) -> Unit,
                      id:String)
    {
        firestore.collection("Coffee")
            .document(id)
            .get()
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    val result = task.result
                    val imageUri = result.getString("imageUri") ?: ""
                    val name = result.getString("name") ?: ""
                    val type = result.getString("type") ?: ""
                    val rating = result.getDouble("rating") ?: 0.0
                    val price = result.getDouble("price") ?: 0.0
                    val description = result.getString("description") ?: ""

                    val coffee = Coffee(result.id,imageUri, name, type, rating, price, description)
                    onSuccess(coffee)
                }
                else{
                    onFailure(task.exception?.message ?:"Bilinmeyen bir hata oluştu!")
                }
            }



    }
}
