package com.example.doggymatch.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organizations")
data class Organizations (
    @PrimaryKey var id: Int? = null,
    @ColumnInfo val name: String?,
    @ColumnInfo val about: String?,
    @ColumnInfo val adoptionProcess: String?,
    @ColumnInfo val address: String?,
    @ColumnInfo val city: String?,
    @ColumnInfo val state: String?,
    @ColumnInfo val zip: String?,
    @ColumnInfo val phone: String?,
    @ColumnInfo val latitude: Float?,
    @ColumnInfo val longitude: Float?,
    @ColumnInfo val email: String?,
    @ColumnInfo val website: String?,
    @ColumnInfo val facebook: String?,
)