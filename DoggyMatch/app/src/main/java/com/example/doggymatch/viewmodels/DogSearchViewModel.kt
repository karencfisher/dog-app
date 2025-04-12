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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogSearchViewModel(
    private val breedId: Int?
) : ViewModel(){

    private val _animals = MutableStateFlow<List<Any>>(emptyList())
    val animals: StateFlow<List<Any>> = _animals

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _postalCode = MutableStateFlow<String>("90210")
    val postalCode: StateFlow<String> = _postalCode

    private val _miles = MutableStateFlow<Int>(50)
    val miles: StateFlow<Int> = _miles

    fun searchAnimals(postalCode: String, miles: Int) {
        if (breedId != null) {
            fetchAnimals(breedId, postalCode, miles)
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
                            postalCode = postalCode
                        )
                    )
                )

                val response = RetrofitClient.api.searchAnimals(body).execute()

                if (response.isSuccessful && response.body() != null) {
                    _animals.value = response.body()!!.data
                } else {
                    _error.value = "API error: ${response.code()}"
                }
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
                val breedId = this[BREED_ID_KEY]
                val app = this[APPLICATION_KEY] as DoggyMatchApplication
                DogSearchViewModel(breedId)
            }
        }
    }
}