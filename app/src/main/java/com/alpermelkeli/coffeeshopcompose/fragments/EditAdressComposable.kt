package com.alpermelkeli.coffeeshopcompose.fragments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.alpermelkeli.coffeeshopcompose.model.Location
import com.alpermelkeli.coffeeshopcompose.viewmodel.UserViewModel

@Composable
fun EditAddressScreen(
    userViewModel: UserViewModel) {
    LaunchedEffect(null) {
        userViewModel.getAllLocations()
    }

    val locations by userViewModel.allLocations.observeAsState()

    var showDialog by remember { mutableStateOf(false) }
    var newAddress by remember { mutableStateOf(TextFieldValue("")) }
    var newLatitude by remember { mutableStateOf(TextFieldValue("")) }
    var newLongitude by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Select Address", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(modifier = Modifier.weight(1f)) {
            locations?.let {
                items(it.size) { index ->
                    val location = locations!![index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(8.dp)
                    ) {
                        Text(location.description)
                    }
                }
            }
        }

        Button(onClick = { showDialog = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Add New Address")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add New Address") },
                text = {
                    Column {
                        TextField(
                            value = newAddress,
                            onValueChange = { newAddress = it },
                            label = { Text("Address Description") }
                        )
                        TextField(
                            value = newLatitude,
                            onValueChange = { newLatitude = it },
                            label = { Text("Latitude") }
                        )
                        TextField(
                            value = newLongitude,
                            onValueChange = { newLongitude = it },
                            label = { Text("Longitude") }
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        val latitude = newLatitude.text.toDoubleOrNull()
                        val longitude = newLongitude.text.toDoubleOrNull()

                        if (latitude != null && longitude != null) {
                            newAddress = TextFieldValue("")
                            newLatitude = TextFieldValue("")
                            newLongitude = TextFieldValue("")
                            showDialog = false
                        }
                    }) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

