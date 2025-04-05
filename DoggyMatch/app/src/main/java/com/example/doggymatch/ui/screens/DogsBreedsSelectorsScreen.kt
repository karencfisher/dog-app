package com.example.doggymatch.ui.screens

import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doggymatch.ui.components.AttributesDropdown
import com.example.doggymatch.viewmodels.DogsBreedsSelectorsViewModel

@Composable
fun DogsBreedsSelectorsScreen(
    viewModel: DogsBreedsSelectorsViewModel = viewModel(
        factory = DogsBreedsSelectorsViewModel.Factory),
) {
    // Your UI code here
    val sizes = viewModel.sizes.collectAsState()
    val popularity = viewModel.popularity.collectAsState()
    val energy = viewModel.energy.collectAsState()
    val trainability = viewModel.trainability.collectAsState()
    val grooming = viewModel.grooming.collectAsState()
    val shedding = viewModel.shedding.collectAsState()
    val demeanor = viewModel.demeanor.collectAsState()
    val friendliness = viewModel.friendliness.collectAsState()

    var selectedSize by remember { mutableStateOf("") }
    var selectedPopularity by remember { mutableStateOf("") }
    var selectedEnergy by remember { mutableStateOf("") }
    var selectedTrainability by remember { mutableStateOf("") }
    var selectedGrooming by remember { mutableStateOf("") }
    var selectedShedding by remember { mutableStateOf("") }
    var selectedDemeanor by remember { mutableStateOf("") }
    var selectedFriendliness by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Select the attributes of the dog you want to adopt",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = "Desired size of the dog?")
        AttributesDropdown("Sizes", sizes.value,
            selectedSize, { selectedSize = it })
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "How common or uncommon the dog is?")
        AttributesDropdown("Popularity", popularity.value,
            selectedPopularity, { selectedPopularity = it })
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "The dog's energy level?")
        AttributesDropdown("Energy", energy.value,
            selectedEnergy, { selectedEnergy = it })
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "How easy is it to train the dog?")
        AttributesDropdown("Trainability", trainability.value,
            selectedTrainability, { selectedTrainability = it })
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "How much grooming does the dog need?")
        AttributesDropdown("Grooming", grooming.value,
            selectedGrooming, { selectedGrooming = it })
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "How much shedding does the dog do?")
        AttributesDropdown("Shedding", shedding.value,
            selectedShedding, { selectedShedding = it })
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "What is the dog's demeanor?")
        AttributesDropdown("Demeanor", demeanor.value,
            selectedDemeanor, { selectedDemeanor = it })
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "How friendly is the dog, including with children and other dogs?")
        AttributesDropdown("Friendliness", friendliness.value,
            selectedFriendliness, { selectedFriendliness = it })
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Button(
            onClick = {
                // Handle the button click
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Find my breed!")
        }
    }
}