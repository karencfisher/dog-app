package com.example.doggymatch.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doggymatch.DoggyMatchApplication
import com.example.doggymatch.ui.components.OrganizationCard
import com.example.doggymatch.viewmodels.OrganizationDetailsViewModel
import com.example.doggymatch.viewmodels.OrganizationDetailsViewModel.Companion.ORG_ID_KEY

@Composable
fun OrganizationDetailsScreen(
    orgId: Int,
    goToOrganizationDetails: (Int) -> Unit,
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
        Text(text = "Loading...")
    } else if (error != null) {
        Text(text = "Error: $error")
    } else {
        // Display organization details
        organization?.let {
            OrganizationCard(it)
        }
    }
}