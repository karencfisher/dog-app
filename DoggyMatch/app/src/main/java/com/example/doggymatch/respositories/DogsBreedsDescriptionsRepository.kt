package com.example.doggymatch.respositories

import com.example.doggymatch.daos.DogsBreedsDescriptionsDao
import com.example.doggymatch.models.DogBreedsDescriptions

class DogsBreedsDescriptionsRepository(
    private val dogsBreedsDescriptionsDao: DogsBreedsDescriptionsDao
) {
    suspend fun getDogBreedDescriptions(ids: List<Int>): List<DogBreedsDescriptions> {
        return dogsBreedsDescriptionsDao.getDogBreedDescriptions(ids)
    }

    suspend fun getSelectedDogBreedDescriptions(): List<DogBreedsDescriptions> {
        return dogsBreedsDescriptionsDao.getSelectedDogBreedDescriptions()
    }

    suspend fun updateSelectedStatus(selectedIds: List<Int>) {
        // First reset all to unselected
        dogsBreedsDescriptionsDao.resetAllSelections()

        // Then set the selected ones
        if (selectedIds.isNotEmpty()) {
            dogsBreedsDescriptionsDao.updateSelectedStatus(selectedIds)
        }
    }
}