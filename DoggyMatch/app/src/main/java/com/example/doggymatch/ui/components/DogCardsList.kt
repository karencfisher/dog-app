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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.doggymatch.models.SelectedDogs
import com.example.doggymatch.viewmodels.DogSearchViewModel

@Composable
fun DogCardsList(
    dogs: List<SelectedDogs>,
    viewModel: DogSearchViewModel,
    goToOrganizationDetails: (Int) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn {
                items(dogs) { dog ->
                    DogCard(
                        dog = dog,
                        viewModel = viewModel,
                        saveFavorite = { favDog ->
                            viewModel.addDogToSelectedDogs(favDog)
                        },
                        removeFavorite = { favDog ->
                            viewModel.removeDogFromSelectedDogs(favDog)
                        },
                        goToOrganizationDetails = { orgId ->
                            goToOrganizationDetails(orgId)
                        }
                    )
                }
            }
        }
    }
}