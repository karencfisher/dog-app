package com.example.doggymatch.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doggymatch.viewmodels.DogsBreedsSelectorsViewModel

@Composable
fun DogsBreedsSelectorsScreen(
    viewModel: DogsBreedsSelectorsViewModel = viewModel(
        factory = DogsBreedsSelectorsViewModel.Factory),
) {
    // Your UI code here
    val sizes = viewModel.sizes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Dog Breeds Sizes")
        LazyColumn {
            items(sizes.value) { size ->
                Text(text = size)
            }
        }
    }
}