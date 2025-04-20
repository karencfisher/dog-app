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
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadSelectedDogBreeds()
    }

    private fun loadSelectedDogBreeds() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _selectedDogBreeds.value = dogsBreedsDescriptionsRepository.getSelectedDogBreedDescriptions()
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
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

