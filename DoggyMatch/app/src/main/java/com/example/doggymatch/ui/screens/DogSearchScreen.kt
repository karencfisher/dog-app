package com.example.doggymatch.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doggymatch.DoggyMatchApplication
import com.example.doggymatch.ui.components.DogCardsList
import com.example.doggymatch.ui.components.ErrorMessage
import com.example.doggymatch.ui.components.LoadingAnimation
import com.example.doggymatch.viewmodels.DogSearchViewModel
import com.example.doggymatch.viewmodels.DogSearchViewModel.Companion.BREED_ID_KEY

@Composable
fun DogSearchScreen(
    breedId: Int,
    breedName: String,
    goToOrganizationDetails: (Int) -> Unit = {},
    viewModel: DogSearchViewModel = viewModel(
        factory = DogSearchViewModel.Factory,
        extras = MutableCreationExtras().apply {
            this[APPLICATION_KEY] = LocalContext.current.applicationContext as DoggyMatchApplication
            this[BREED_ID_KEY] = breedId
        })
) {
    val dogs by viewModel.dogs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val postalCode by viewModel.postalCode.collectAsState()
    val miles by viewModel.miles.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    fun searchAnimals() {
        viewModel.searchAnimals()
        keyboardController?.hide()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Search for $breedName",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = postalCode,
                onValueChange = { viewModel.setPostalCode(it) },
                label = { Text("My Postal Code") }
            )
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                value = miles.toString(),
                onValueChange = { viewModel.setMiles(it.toIntOrNull() ?: 0) },
                label = { Text("Miles I'll Travel") }
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Button(
                onClick = { searchAnimals() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = "Search for your next best friend")
            }
        }

        if (isLoading) {
            LoadingAnimation(
                pawSize = 50,
                pawSpacing = 50
            )
        } else if (error != null) {
            println("Error: $error")
            ErrorMessage()
        } else if (dogs.isEmpty()) {
            ErrorMessage(
                message = "No dogs found",
                note = "Please try again with a different postal code or distance",
                kind = "info"
            )
        } else {
            DogCardsList(
                dogs = dogs,
                viewModel = viewModel,
                goToOrganizationDetails = { orgId ->
                    goToOrganizationDetails(orgId)
                }
            )
        }
    }
}
