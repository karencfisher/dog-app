package com.example.doggymatch.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.doggymatch.models.DogBreedsDescriptions

@Dao
abstract class DogsBreedsDescriptionsDao {
    @Query("SELECT * FROM dogBreedsDescription WHERE selected = 1")
    abstract suspend fun getSelectedDogBreedDescriptions(): List<DogBreedsDescriptions>

    @Query("UPDATE DogBreedsDescription SET selected = 0, matchScore = 0")
    abstract suspend fun resetAllSelections()

    @Query("UPDATE DogBreedsDescription SET selected = 1, matchScore = :matchScore WHERE breedId = :breedId")
    abstract suspend fun updateSelectedStatusWithScore(breedId: Int, matchScore: Int)
}