package com.example.doggymatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "selectedDogs",
    foreignKeys = [
    ForeignKey(
        entity = DogBreedsDescriptions::class,
        parentColumns = ["breedId"],
        childColumns = ["breedId"],
        onDelete = ForeignKey.NO_ACTION
    ),
    ForeignKey(
        entity = Organizations::class,
        parentColumns = ["id"],
        childColumns = ["orgId"],
        onDelete = ForeignKey.NO_ACTION
    )
])
data class SelectedDogs (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo val breedId: Int?,
    @ColumnInfo val orgId: Int?,
    @ColumnInfo val dogId: Int?,
    @ColumnInfo val name: String?,
    @ColumnInfo val sex: String?,
    @ColumnInfo val age: Int?,
    @ColumnInfo val size: String?,
    @ColumnInfo val description: String?,
    @ColumnInfo val imageUrl: String?,
)