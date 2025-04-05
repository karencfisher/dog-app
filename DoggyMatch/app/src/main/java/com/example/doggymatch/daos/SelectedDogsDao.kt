package com.example.doggymatch.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.doggymatch.models.SelectedDogs

@Dao
abstract class SelectedDogsDao {
    @Query("SELECT * FROM SelectedDogs")
    abstract suspend fun getAllSelectedDogs(): List<SelectedDogs>

    @Query("SELECT * FROM SelectedDogs " +
           "INNER JOIN Organizations ON SelectedDogs.orgId = Organizations.id " +
           "WHERE SelectedDogs.id = :id")
    abstract suspend fun getSelectedDogById(id: Int): SelectedDogs?

    @Insert
    abstract suspend fun insertSelectedDog(selectedDog: SelectedDogs)

    @Update
    abstract suspend fun updateSelectedDog(selectedDog: SelectedDogs)

    @Delete
    abstract suspend fun deleteSelectedDog(selectedDog: SelectedDogs)
}