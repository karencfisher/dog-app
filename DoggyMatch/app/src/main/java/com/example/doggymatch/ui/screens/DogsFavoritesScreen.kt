package com.example.doggymatch.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doggymatch.DoggyMatchApplication
import com.example.doggymatch.viewmodels.DogSearchViewModel
import com.example.doggymatch.ui.components.DogCardsList
import com.example.doggymatch.ui.components.ErrorMessage
import com.example.doggymatch.ui.components.LoadingAnimation

@Composable
fun DogsFavoritesScreen(
    goToOrganizationDetails: (Int) -> Unit = {},
    viewModel: DogSearchViewModel = viewModel(
        factory = DogSearchViewModel.Factory,
        extras = MutableCreationExtras().apply {
            this[APPLICATION_KEY] = LocalContext.current.applicationContext as DoggyMatchApplication
        })
) {
    val dogs by viewModel.dogs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getSelectedDogs()
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
                text = "Dog Favorites",
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
        } else if (dogs.isEmpty()) {
            Text(
                text = "No favorite dogs found",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
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