package com.example.doggymatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organizations")
data class organizations (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo val name: String,
    
)