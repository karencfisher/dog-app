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

    fun getIdByAllFields(
        size: String,
        popularity: String,
        energy: String,
        trainability: String,
        grooming: String,
        shedding: String,
        demeanor: String,
        friendliness: String
    ) {
        viewModelScope.launch {
            // Get the breed IDs that match the criteria
            val breedIds = dogsBreedsSelectorsRepository.getIdByAllFields(
                size, popularity, energy, trainability, grooming, shedding, demeanor, friendliness
            ) ?: emptyList()

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