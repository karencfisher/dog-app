package com.example.doggymatch.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class DogsBreedsSelectorsDao {
    @Query("SELECT DISTINCT size FROM DogBreedsSelectors")
    abstract suspend fun getSizes(): List<String>

    @Query("SELECT DISTINCT popularity FROM DogBreedsSelectors")
    abstract suspend fun getPopularity(): List<String>

    @Query("SELECT DISTINCT energy FROM DogBreedsSelectors")
    abstract suspend fun getEnergies(): List<String>

    @Query("SELECT DISTINCT trainability FROM DogBreedsSelectors")
    abstract suspend fun getTrainability(): List<String>

    @Query("SELECT DISTINCT grooming FROM DogBreedsSelectors")
    abstract suspend fun getGrooming(): List<String>

    @Query("SELECT DISTINCT shedding FROM DogBreedsSelectors")
    abstract suspend fun getShedding(): List<String>

    @Query("SELECT DISTINCT demeanor FROM DogBreedsSelectors")
    abstract suspend fun getDemeanor(): List<String>

    @Query("SELECT DISTINCT friendliness FROM DogBreedsSelectors")
    abstract suspend fun getFriendliness(): List<String>

    @Query("""
        SELECT id FROM DogBreedsSelectors
        WHERE size = :size AND popularity = :popularity AND energy = :energy AND
              trainability = :trainability AND grooming = :grooming AND
              shedding = :shedding AND demeanor = :demeanor AND
              friendliness = :friendliness
    """)
    abstract suspend fun getIdByAllFields(
        size: String,
        popularity: String,
        energy: String,
        trainability: String,
        grooming: String,
        shedding: String,
        demeanor: String,
        friendliness: String
    ): List<Int>?
}