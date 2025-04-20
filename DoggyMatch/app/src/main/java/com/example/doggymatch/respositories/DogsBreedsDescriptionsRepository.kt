package com.example.doggymatch.respositories

import com.example.doggymatch.daos.DogsBreedsDescriptionsDao
import com.example.doggymatch.daos.DogsBreedsSelectorsDao
import com.example.doggymatch.models.DogBreedsDescriptions

class DogsBreedsDescriptionsRepository(
    private val dogsBreedsDescriptionsDao: DogsBreedsDescriptionsDao
) {
    suspend fun getSelectedDogBreedDescriptions(): List<DogBreedsDescriptions> {
        return dogsBreedsDescriptionsDao.getSelectedDogBreedDescriptions()
    }

    suspend fun updateSelectedStatus(selBreeds: List<DogsBreedsSelectorsDao.BreedMatch>) {
        // First reset all to unselected
        dogsBreedsDescriptionsDao.resetAllSelections()

        for (breed in selBreeds) {
            dogsBreedsDescriptionsDao.updateSelectedStatusWithScore(
                breed.breedId,
                breed.matchScore
            )
        }
    }
}