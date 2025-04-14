package com.example.doggymatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selectedDogs")
data class SelectedDogs (
    @PrimaryKey var dogId: Int? = null,
    @ColumnInfo val orgId: Int?,
    @ColumnInfo val name: String?,
    @ColumnInfo val distance: Double?,
    @ColumnInfo val breed: String?,
    @ColumnInfo val sex: String?,
    @ColumnInfo val age: String?,
    @ColumnInfo val size: String?,
    @ColumnInfo val description: String?,
    @ColumnInfo val imageUrl: String?,
)