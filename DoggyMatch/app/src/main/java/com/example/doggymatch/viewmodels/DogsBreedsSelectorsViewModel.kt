package com.example.doggymatch.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.doggymatch.DoggyMatchApplication
import com.example.doggymatch.respositories.DogsBreedsDescriptionsRepository


import com.example.doggymatch.respositories.DogsBreedsSelectorsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DogsBreedsSelectorsViewModel(
    private val dogsBreedsSelectorsRepository: DogsBreedsSelectorsRepository,
    private val dogBreedsDescriptionsRepository: DogsBreedsDescriptionsRepository
) : ViewModel() {
    private val _sizes = MutableStateFlow(emptyList<String>())
    val sizes: StateFlow<List<String>> = _sizes

    private val _popularity = MutableStateFlow(emptyList<String>())
    val popularity: StateFlow<List<String>> = _popularity

    private val _energy = MutableStateFlow(emptyList<String>())
    val energy: StateFlow<List<String>> = _energy

    private val _trainability = MutableStateFlow(emptyList<String>())
    val trainability: StateFlow<List<String>> = _trainability

    private val _grooming = MutableStateFlow(emptyList<String>())
    val grooming: StateFlow<List<String>> = _grooming

    private val _shedding = MutableStateFlow(emptyList<String>())
    val shedding: StateFlow<List<String>> = _shedding

    private val _demeanor = MutableStateFlow(emptyList<String>())
    val demeanor: StateFlow<List<String>> = _demeanor

    private val _friendliness = MutableStateFlow(emptyList<String>())
    val friendliness: StateFlow<List<String>> = _friendliness

    // selected attributes states
    private val _selectedSize = MutableStateFlow("")
    var selectedSize: StateFlow<String> = _selectedSize
    private val _selectedPopularity = MutableStateFlow("")
    var selectedPopularity: StateFlow<String> = _selectedPopularity
    private val _selectedEnergy = MutableStateFlow("")
    var selectedEnergy: StateFlow<String> = _selectedEnergy
    private val _selectedTrainability = MutableStateFlow("")
    var selectedTrainability: StateFlow<String> = _selectedTrainability
    private val _selectedGrooming = MutableStateFlow("")
    var selectedGrooming: StateFlow<String> = _selectedGrooming
    private val _selectedShedding = MutableStateFlow("")
    var selectedShedding: StateFlow<String> = _selectedShedding
    private val _selectedDemeanor = MutableStateFlow("")
    var selectedDemeanor: StateFlow<String> = _selectedDemeanor
    private val _selectedFriendliness = MutableStateFlow("")
    var selectedFriendliness: StateFlow<String> = _selectedFriendliness

    // Function to update the selected attributes
    // Add these update methods
    fun updateSelectedSize(size: String) {
        _selectedSize.value = size
    }

    fun updateSelectedPopularity(popularity: String) {
        _selectedPopularity.value = popularity
    }

    fun updateSelectedEnergy(energy: String) {
        _selectedEnergy.value = energy
    }

    fun updateSelectedTrainability(trainability: String) {
        _selectedTrainability.value = trainability
    }

    fun updateSelectedGrooming(grooming: String) {
        _selectedGrooming.value = grooming
    }

    fun updateSelectedShedding(shedding: String) {
        _selectedShedding.value = shedding
    }

    fun updateSelectedDemeanor(demeanor: String) {
        _selectedDemeanor.value = demeanor
    }

    fun updateSelectedFriendliness(friendliness: String) {
        _selectedFriendliness.value = friendliness
    }

    // Modify getIdByAllFields to use the stored state values
    fun getIdByFields(): Job {
        return viewModelScope.launch {
            // Get the breed IDs that match the criteria using internal state values
            val breedIds = dogsBreedsSelectorsRepository.getIdByFields(
                _selectedSize.value,
                _selectedPopularity.value,
                _selectedEnergy.value,
                _selectedTrainability.value,
                _selectedGrooming.value,
                _selectedShedding.value,
                _selectedDemeanor.value,
                _selectedFriendliness.value
            )

            // Update the selected status in the descriptions table
            dogBreedsDescriptionsRepository.updateSelectedStatus(breedIds)
        }
    }

    init {
        viewModelScope.launch {
            _sizes.value = dogsBreedsSelectorsRepository.getSizes()
            _popularity.value = dogsBreedsSelectorsRepository.getPopularity()
            _energy.value = dogsBreedsSelectorsRepository.getEnergy()
            _trainability.value = dogsBreedsSelectorsRepository.getTrainability()
            _grooming.value = dogsBreedsSelectorsRepository.getGrooming()
            _shedding.value = dogsBreedsSelectorsRepository.getShedding()
            _demeanor.value = dogsBreedsSelectorsRepository.getDemeanor()
            _friendliness.value = dogsBreedsSelectorsRepository.getFriendliness()
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as DoggyMatchApplication
                DogsBreedsSelectorsViewModel(
                    application.dogBreedsSelectorsRepository,
                    application.dogBreedsDescriptionsRepository
                )
            }
        }
    }
}