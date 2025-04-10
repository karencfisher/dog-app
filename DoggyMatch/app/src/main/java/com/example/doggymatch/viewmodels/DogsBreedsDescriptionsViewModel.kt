package com.example.doggymatch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.doggymatch.DoggyMatchApplication
import com.example.doggymatch.models.DogBreedsDescriptions
import com.example.doggymatch.respositories.DogsBreedsDescriptionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogsBreedsDescriptionsViewModel(
    private val dogsBreedsDescriptionsRepository: DogsBreedsDescriptionsRepository
) : ViewModel() {
    private val _selectedDogBreeds = MutableStateFlow<List<DogBreedsDescriptions>>(emptyList())
    val selectedDogBreeds: StateFlow<List<DogBreedsDescriptions>> = _selectedDogBreeds

    init {
        loadSelectedDogBreeds()
    }

    private fun loadSelectedDogBreeds() {
        viewModelScope.launch {
            _selectedDogBreeds.value = dogsBreedsDescriptionsRepository.getSelectedDogBreedDescriptions()
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as DoggyMatchApplication
                DogsBreedsDescriptionsViewModel(
                    application.dogBreedsDescriptionsRepository
                )
            }
        }
    }
}

