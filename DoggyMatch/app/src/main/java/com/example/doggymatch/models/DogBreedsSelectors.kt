package com.example.doggymatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogBreedsSelectors")
data class DogBreedsSelectors(
    @PrimaryKey var breedId: Int?,
    @ColumnInfo(name = "size") val size: String,
    @ColumnInfo(name = "popularity") val popularity: String,
    @ColumnInfo(name = "energy") val energy: String,
    @ColumnInfo(name = "trainability") val trainability: String,
    @ColumnInfo(name = "grooming") val grooming: String,
    @ColumnInfo(name = "shedding") val shedding: String,
    @ColumnInfo(name = "demeanor") val demeanor: String,
    @ColumnInfo(name = "friendliness") val friendliness: String,
)

