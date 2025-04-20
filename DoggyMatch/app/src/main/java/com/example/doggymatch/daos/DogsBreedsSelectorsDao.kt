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
    abstract suspend fun getEnergy(): List<String>

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
        SELECT breedId,
        (CASE WHEN size = :size THEN 1 ELSE 0 END +
         CASE WHEN popularity = :popularity THEN 1 ELSE 0 END +
         CASE WHEN energy = :energy THEN 1 ELSE 0 END +
         CASE WHEN trainability = :trainability THEN 1 ELSE 0 END +
         CASE WHEN grooming = :grooming THEN 1 ELSE 0 END +
         CASE WHEN shedding = :shedding THEN 1 ELSE 0 END +
         CASE WHEN demeanor = :demeanor THEN 1 ELSE 0 END +
         CASE WHEN friendliness = :friendliness THEN 1 ELSE 0 END)
         AS matchScore
        FROM DogBreedsSelectors
        WHERE matchScore >= 6
        ORDER BY matchScore DESC
        LIMIT 5
    """)
    abstract suspend fun getIdByFields(
        size: String,
        popularity: String,
        energy: String,
        trainability: String,
        grooming: String,
        shedding: String,
        demeanor: String,
        friendliness: String
    ): List<BreedMatch>

    // Data class to hold the result
    data class BreedMatch(
        val breedId: Int,
        val matchScore: Int
    )
}