package com.example.doggymatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogBreedsSelectors")
data class DogBreedsSelectors(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo val size: String,
    @ColumnInfo val popularity: String,
    @ColumnInfo val energy: String,
    @ColumnInfo val trainability: String,
    @ColumnInfo val grooming: String,
    @ColumnInfo val shedding: String,
    @ColumnInfo val demeanor: String,
    @ColumnInfo val friendliness: String,
)

