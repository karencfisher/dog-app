package com.example.doggymatch.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.doggymatch.models.DogBreedsDescriptions

@Dao
abstract class DogsBreedsDescriptionsDao {
    @Query("SELECT * FROM dogBreedsDescription WHERE breedId IN (:ids)")
    abstract suspend fun getDogBreedDescriptions(ids: List<Int>): List<DogBreedsDescriptions>

    @Query("SELECT * FROM dogBreedsDescription WHERE selected = 1")
    abstract suspend fun getSelectedDogBreedDescriptions(): List<DogBreedsDescriptions>

    @Query("UPDATE DogBreedsDescription SET selected = 0")
    abstract suspend fun resetAllSelections()

    @Query("UPDATE DogBreedsDescription SET selected = 1 WHERE breedId IN (:ids)")
    abstract suspend fun updateSelectedStatus(ids: List<Int>)
}