package com.example.doggymatch.respositories

import com.example.doggymatch.daos.DogsBreedsDescriptionsDao
import com.example.doggymatch.models.DogBreedsDescriptions

class DogsBreedsDescriptionsRepository(
    private val dogsBreedsDescriptionsDao: DogsBreedsDescriptionsDao
) {
    suspend fun getDogBreedDescriptions(ids: List<Int>): List<DogBreedsDescriptions> {
        return dogsBreedsDescriptionsDao.getDogBreedDescriptions(ids)
    }
}