package com.example.doggymatch.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.doggymatch.models.DogBreedsDescriptions

@Dao
abstract class DogsBreedsDescriptionsDao {
    @Query("SELECT * FROM dogBreedsDescription WHERE breedId IN (:ids)")
    abstract suspend fun getDogBreedDescriptions(ids: List<Int>): List<DogBreedsDescriptions>
}