package com.example.doggymatch.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.doggymatch.models.DogBreedsDescriptions

@Dao
abstract class DogsBreedsDescriptionsDao {
    @Query("SELECT * FROM DogBreedsDescriptions WHERE id = :id")
    abstract suspend fun getDogBreedDescription(id: Int): DogBreedsDescriptions?
}