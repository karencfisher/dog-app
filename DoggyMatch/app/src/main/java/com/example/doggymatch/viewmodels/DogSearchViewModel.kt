package com.example.doggymatch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.doggymatch.DoggyMatchApplication
import com.example.doggymatch.apis.DataWrapper
import com.example.doggymatch.apis.Filter
import com.example.doggymatch.apis.FilterRadius
import com.example.doggymatch.apis.RescueRequestBody
import com.example.doggymatch.datasources.RetrofitClient
import com.example.doggymatch.models.Animal
import com.example.doggymatch.models.SelectedDogs
import com.example.doggymatch.respositories.SelectedDogsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogSearchViewModel(
    private val breedId: Int?,
    private val selectedDogsRepository: SelectedDogsRepository
) : ViewModel() {

    private var _animals: List<Animal> = emptyList()

    private val _dogs = MutableStateFlow<List<SelectedDogs>>(emptyList())
    val dogs: StateFlow<List<SelectedDogs>> = _dogs

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isDogSelected = MutableStateFlow(false)
    val isDogSelected: StateFlow<Boolean> = _isDogSelected

    private val _postalCode = MutableStateFlow("90210")
    val postalCode: StateFlow<String> = _postalCode

    private val _miles = MutableStateFlow(50)
    val miles: StateFlow<Int> = _miles

    init {
        // Initialize with default values or fetch from a repository
        _postalCode.value = "90210"
        _miles.value = 50

        if (breedId != null) {
            fetchAnimals(breedId, _postalCode.value, _miles.value)
        } else {
            _error.value = "Breed ID is not set"
        }
    }

    fun searchAnimals(postalCode: String, miles: Int) {
        if (breedId != null) {
            fetchAnimals(breedId, _postalCode.value, miles)
        } else {
            _error.value = "Breed ID is not set"
        }
    }

    fun setPostalCode(postalCode: String) {
        _postalCode.value = postalCode
    }

    fun setMiles(miles: Int) {
        _miles.value = miles
    }

    fun addDogToSelectedDogs(dog: SelectedDogs) {
        viewModelScope.launch {
            try {
                selectedDogsRepository.addSelectedDog(dog)
            } catch (e: Exception) {
                // Check if it's a SQLite constraint exception (likely PRIMARY KEY or UNIQUE constraint)
                if (e.message?.contains("UNIQUE constraint failed", ignoreCase = true) == true ||
                    e.message?.contains("constraint violation", ignoreCase = true) == true) {
                    // Dog already exists in DB, silently ignore
                } else {
                    // For other exceptions, update error state
                    _error.value = "Failed to add dog: ${e.message}"
                }
            }
        }
    }

    fun removeDogFromSelectedDogs(dog: SelectedDogs) {
        viewModelScope.launch {
            try {
                selectedDogsRepository.removeSelectedDog(dog)
            } catch (e: Exception) {
                _error.value = "Failed to remove dog: ${e.message}"
            }
        }
    }
    fun getSelectedDogs() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val selectedDogs = selectedDogsRepository.getSelectedDogs()
                _dogs.value = selectedDogs
            } catch (e: Exception) {
                _error.value = "Failed to fetch selected dogs: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun checkIfDogIsSelected(dogId: Int) {
        viewModelScope.launch {
            try {
                _isDogSelected.value = selectedDogsRepository.getSelectedDogById(dogId) != null
            } catch (e: Exception) {
                _error.value = "Failed to check if dog is in selected dogs: ${e.message}"
            }
        }
    }

    private fun makeSelectedDogs() {
        _dogs.value = emptyList()
        for (selectedDog in _animals) {
            val newDog = SelectedDogs(
                dogId = selectedDog.id.toIntOrNull() ?: 0,
                orgId = selectedDog.relationships.orgs?.data?.firstOrNull()?.id?.toIntOrNull() ?: 0,
                name = selectedDog.attributes.name,
                distance = selectedDog.attributes.distance,
                breed = selectedDog.attributes.breedString,
                sex = selectedDog.attributes.sex,
                age = selectedDog.attributes.ageString,
                size = selectedDog.attributes.sizeGroup,
                description = selectedDog.attributes.cleanDescription,
                imageUrl = selectedDog.attributes.largerThumbnailUrl,
            )
            _dogs.value = _dogs.value.plus(newDog)
        }
    }

    private fun fetchAnimals(breedId: Int, postalCode: String, miles: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val body = RescueRequestBody(
                    data = DataWrapper(
                        filters = listOf(
                            Filter(
                                fieldName = "animals.breedPrimaryId",
                                operation = "equal",
                                criteria = breedId.toString()
                            )
                        ),
                        filterProcessing = "1",
                        filterRadius = FilterRadius(
                            miles = miles,
                            postalcode = postalCode
                        )
                    )
                )

                // Use suspend function instead of execute()
                val response = RetrofitClient.api.searchAnimals(body)
                _animals = response.data as? List<Animal> ?: emptyList()
                makeSelectedDogs()

            } catch (e: Exception) {
                _error.value = "Network error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    companion object {
        val BREED_ID_KEY = object: CreationExtras.Key<Int> {}
        val Factory = viewModelFactory {
            initializer {
                // Try to get breedId from extras, but don't fail if missing
                val breedId = runCatching { this[BREED_ID_KEY] }.getOrNull()
                val app = this[APPLICATION_KEY] as DoggyMatchApplication
                DogSearchViewModel(
                    breedId,
                    app.selectedDogsRepository
                )
            }
        }
    }
}