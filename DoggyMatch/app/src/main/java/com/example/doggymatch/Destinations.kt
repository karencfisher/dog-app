package com.example.doggymatch

import kotlinx.serialization.Serializable

class Destinations {
    @Serializable object Home
    @Serializable object BreedSelection
    @Serializable object BreedDescriptions
    @Serializable class DogSearch(val breedId: Int, val breedName: String)
    @Serializable object DogDetails
    @Serializable class OrganizationDetails(val orgId: Int)
}