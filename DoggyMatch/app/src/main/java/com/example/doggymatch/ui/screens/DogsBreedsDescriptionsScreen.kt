package com.example.doggymatch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doggymatch.ui.components.DogBreedCard
import com.example.doggymatch.ui.components.ErrorMessage
import com.example.doggymatch.ui.components.LoadingAnimation
import com.example.doggymatch.viewmodels.DogsBreedsDescriptionsViewModel

@Composable
fun DogsBreedsDescriptionsScreen(
    goToDogSearch: (Int, String) -> Unit,
    viewModel: DogsBreedsDescriptionsViewModel = viewModel(factory = DogsBreedsDescriptionsViewModel.Factory)
) {
    val selectedDogBreeds by viewModel.selectedDogBreeds.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Selected Dog Breeds",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        if (isLoading) {
            LoadingAnimation(
                pawSize = 50,
                pawSpacing = 50
            )
        } else if (error != null) {
            println("Error: $error")
            ErrorMessage()
        } else if (selectedDogBreeds.isEmpty()) {
            ErrorMessage(
                message = "No dog breeds match your selection",
                kind = "info"
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(selectedDogBreeds) { breed ->
                    DogBreedCard(
                        breed = breed,
                        goToDogSearch = { breedId ->
                            goToDogSearch(breedId, breed.name)
                        })
                }
            }
        }
    }
}


