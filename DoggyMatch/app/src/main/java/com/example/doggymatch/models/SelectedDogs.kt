package com.example.doggymatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    androidx.room.ForeignKey(
        entity = DogBreedsDescriptions::class,
        parentColumns = ["id"],
        childColumns = ["breedId"],
        onDelete = androidx.room.ForeignKey.CASCADE
    ),
    androidx.room.ForeignKey(
        entity = Organizations::class,
        parentColumns = ["id"],
        childColumns = ["orgId"],
        onDelete = androidx.room.ForeignKey.CASCADE
    )
])
data class SelectedDogs (
    @PrimaryKey(autoGenerate = true) var selectedDogId: Int? = null,
    @ColumnInfo val breedId: Int,
    @ColumnInfo val orgId: Int,
    @ColumnInfo val dogId: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val sex: String,
    @ColumnInfo val age: Int,
    @ColumnInfo val size: String,
    @ColumnInfo val description: String,
    @ColumnInfo val imageUrl: String,
)