package com.example.doggymatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogBreedsDescription")
data class DogBreedsDescriptions(
    @PrimaryKey var breedId: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "temperament") val temperament: String,
    @ColumnInfo(name = "maxLifeExpectancy") val maxLifeExpectancy: Int,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "rescueApiId") val rescueApiId: Int,
    @ColumnInfo(name = "selected") val selected: Int = 0,
    @ColumnInfo(name = "matchScore") val matchScore: Int = 0,
)
