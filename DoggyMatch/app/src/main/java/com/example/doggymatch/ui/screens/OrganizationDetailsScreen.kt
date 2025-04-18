package com.example.doggymatch.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doggymatch.DoggyMatchApplication
import com.example.doggymatch.ui.components.LoadingAnimation
import com.example.doggymatch.ui.components.OrganizationCard
import com.example.doggymatch.viewmodels.OrganizationDetailsViewModel
import com.example.doggymatch.viewmodels.OrganizationDetailsViewModel.Companion.ORG_ID_KEY

@Composable
fun OrganizationDetailsScreen(
    orgId: Int,
    goToMap: (String, Double, Double) -> Unit = { _, _, _ -> },
    viewModel: OrganizationDetailsViewModel = viewModel(
        factory = OrganizationDetailsViewModel.Factory,
        extras = MutableCreationExtras().apply {
            this[APPLICATION_KEY] = LocalContext.current.applicationContext as DoggyMatchApplication
            this[ORG_ID_KEY] = orgId
        }
    )
) {
    val organization by viewModel.organization.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    if (isLoading) {
        LoadingAnimation(
            pawSize = 50,
            pawSpacing = 50
        )
    } else if (error != null) {
        println("Error: $error")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "An error occurred",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
    } else {
        // Display organization details
        organization?.let {
            OrganizationCard(it, goToMap)
        }
    }
}