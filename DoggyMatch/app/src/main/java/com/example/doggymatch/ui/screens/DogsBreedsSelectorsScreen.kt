package com.example.doggymatch.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doggymatch.ui.components.AttributesDropdown
import com.example.doggymatch.viewmodels.DogsBreedsSelectorsViewModel

@Composable
    fun DogsBreedsSelectorsScreen(
        goToDescriptions: () -> Unit,
        viewModel: DogsBreedsSelectorsViewModel = viewModel(
            factory = DogsBreedsSelectorsViewModel.Factory),
    ) {
        // Collect StateFlow values as immutable State objects
        val sizes = viewModel.sizes.collectAsState()
        val popularity = viewModel.popularity.collectAsState()
        val energy = viewModel.energy.collectAsState()
        val trainability = viewModel.trainability.collectAsState()
        val grooming = viewModel.grooming.collectAsState()
        val shedding = viewModel.shedding.collectAsState()
        val demeanor = viewModel.demeanor.collectAsState()
        val friendliness = viewModel.friendliness.collectAsState()

        // Collect selected values from ViewModel
        val selectedSize = viewModel.selectedSize.collectAsState()
        val selectedPopularity = viewModel.selectedPopularity.collectAsState()
        val selectedEnergy = viewModel.selectedEnergy.collectAsState()
        val selectedTrainability = viewModel.selectedTrainability.collectAsState()
        val selectedGrooming = viewModel.selectedGrooming.collectAsState()
        val selectedShedding = viewModel.selectedShedding.collectAsState()
        val selectedDemeanor = viewModel.selectedDemeanor.collectAsState()
        val selectedFriendliness = viewModel.selectedFriendliness.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) { Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Select Breed Attributes",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
            Text(text = "Desired size of the dog?")
            AttributesDropdown("Sizes", sizes.value,
                selectedSize.value
            ) { viewModel.updateSelectedSize(it) }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(text = "How common or uncommon the dog is?")
            AttributesDropdown("Popularity", popularity.value,
                selectedPopularity.value, { viewModel.updateSelectedPopularity(it) })
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(text = "The dog's energy level?")
            AttributesDropdown("Energy", energy.value,
                selectedEnergy.value, { viewModel.updateSelectedEnergy(it) })
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(text = "How easy is it to train the dog?")
            AttributesDropdown("Trainability", trainability.value,
                selectedTrainability.value, { viewModel.updateSelectedTrainability(it) })
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(text = "How much grooming does the dog need?")
            AttributesDropdown("Grooming", grooming.value,
                selectedGrooming.value, { viewModel.updateSelectedGrooming(it) })
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(text = "How much shedding does the dog do?")
            AttributesDropdown("Shedding", shedding.value,
                selectedShedding.value, { viewModel.updateSelectedShedding(it) })
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(text = "What is the dog's demeanor?")
            AttributesDropdown("Demeanor", demeanor.value,
                selectedDemeanor.value, { viewModel.updateSelectedDemeanor(it) })
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(text = "How friendly is the dog, including with children and other dogs?")
            AttributesDropdown("Friendliness", friendliness.value,
                selectedFriendliness.value, { viewModel.updateSelectedFriendliness(it) })
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Button(
                onClick = {
                    // The ViewModel already has the selected values
                    viewModel.getIdByAllFields()
                    goToDescriptions()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Find my breed!")
            }
        }
    }