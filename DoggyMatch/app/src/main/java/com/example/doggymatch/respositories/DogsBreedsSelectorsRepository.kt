package com.example.doggymatch.respositories

import com.example.doggymatch.daos.DogsBreedsSelectorsDao

class DogsBreedsSelectorsRepository(
    private val dogsBreedsSelectorsDao: DogsBreedsSelectorsDao
) {
    suspend fun getSizes(): List<String> {
        return dogsBreedsSelectorsDao.getSizes()
    }
    suspend fun getPopularity(): List<String> {
        return dogsBreedsSelectorsDao.getPopularity()
    }
    suspend fun getEnergy(): List<String> {
        return dogsBreedsSelectorsDao.getEnergy()
    }
    suspend fun getTrainability(): List<String> {
        return dogsBreedsSelectorsDao.getTrainability()
    }
    suspend fun getGrooming(): List<String> {
        return dogsBreedsSelectorsDao.getGrooming()
    }
    suspend fun getShedding(): List<String> {
        return dogsBreedsSelectorsDao.getShedding()
    }
    suspend fun getDemeanor(): List<String> {
        return dogsBreedsSelectorsDao.getDemeanor()
    }
    suspend fun getFriendliness(): List<String> {
        return dogsBreedsSelectorsDao.getFriendliness()
    }
    suspend fun getIdByAllFields(
        size: String,
        popularity: String,
        energy: String,
        trainability: String,
        grooming: String,
        shedding: String,
        demeanor: String,
        friendliness: String
    ): List<Int>? {
        return dogsBreedsSelectorsDao.getIdByAllFields(
            size, popularity, energy, trainability, grooming, shedding, demeanor, friendliness
        )
    }

}