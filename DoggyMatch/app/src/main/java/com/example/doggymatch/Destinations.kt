package com.example.doggymatch

import kotlinx.serialization.Serializable

class Destinations {
    @Serializable object Home
    @Serializable object BreedSelection
    @Serializable object BreedDescriptions
    @Serializable class DogSearch(val breedId: Int)
    @Serializable object DogDetails
}