package com.example.doggymatch.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.doggymatch.models.SelectedDogs
import com.example.doggymatch.viewmodels.DogSearchViewModel
import kotlinx.coroutines.launch

@Composable
fun DogCardsList(
    dogs: List<SelectedDogs>,
    viewModel: DogSearchViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    fun isDogInSelectedDogs(dogId: Int): Boolean {
        // This function cannot directly return from the coroutine scope
        // Instead, return a placeholder value
        coroutineScope.launch {
            viewModel.isDogInSelectedDogs(dogId)
        }
        return false // This will always return false immediately
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (dogs.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No dogs found",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "If searching adjust the postal code and/or miles, and try again",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                LazyColumn {
                    items(dogs) { dog ->
                        DogCard(
                            dog = dog,
                            saveFavorite = { favDog ->
                                viewModel.addDogToSelectedDogs(favDog)
                            },
                            removeFavorite = { favDog ->
                                viewModel.removeDogFromSelectedDogs(favDog)
                            },
                            isFavorite = { dogId, callback ->
                                coroutineScope.launch {
                                    val isFavorite = viewModel.isDogInSelectedDogs(dogId)
                                    callback(isFavorite)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}