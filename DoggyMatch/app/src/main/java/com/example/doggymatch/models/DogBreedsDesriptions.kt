package com.example.doggymatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogBreedsDescriptions")
data class DogBreedsDescriptions(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val temperament: String,
    @ColumnInfo val maxLifeExpectancy: String,
    @ColumnInfo val imageUrl: String,
    @ColumnInfo val rescueApiId: Int,
    @ColumnInfo val selected: Boolean = false,
)
