package com.example.doggymatch.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.doggymatch.viewmodels.DogsBreedsDescriptionsViewModel

@Composable
fun DogsBreedsDescriptionsScreen(
    goToDogSearch: (Int, String) -> Unit,
    viewModel: DogsBreedsDescriptionsViewModel = viewModel(factory = DogsBreedsDescriptionsViewModel.Factory)
) {
    val selectedDogBreeds by viewModel.selectedDogBreeds.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {  // Add this Box wrapper
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Selected Dog Breeds",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (selectedDogBreeds.isEmpty()) {
                // Display a message when no breeds match
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No dog breeds match your selection.",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                // Display the list of breeds
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
}


