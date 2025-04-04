package com.example.doggymatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Organizations (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo val name: String,
    @ColumnInfo val about: String,
    @ColumnInfo val adoptionProcess: String,
    @ColumnInfo val distance: Int,
    @ColumnInfo val address: String,
    @ColumnInfo val city: String,
    @ColumnInfo val state: String,
    @ColumnInfo val zip: String,
    @ColumnInfo val email: String,
)