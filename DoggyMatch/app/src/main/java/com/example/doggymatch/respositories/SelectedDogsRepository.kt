package com.example.doggymatch.respositories

import com.example.doggymatch.daos.SelectedDogsDao
import com.example.doggymatch.models.SelectedDogs

class SelectedDogsRepository(
    private val selectedDogsDao: SelectedDogsDao
) {
    suspend fun getSelectedDogs(): List<SelectedDogs> {
        return selectedDogsDao.getAllSelectedDogs()
    }

    suspend fun getSelectedDogById(id: Int): SelectedDogs? {
        return selectedDogsDao.getSelectedDogById(id)
    }

    suspend fun addSelectedDog(selectedDog: SelectedDogs) {
        selectedDogsDao.insertSelectedDog(selectedDog)
    }

    suspend fun removeSelectedDog(selectedDog: SelectedDogs) {
        selectedDogsDao.deleteSelectedDog(selectedDog)
    }
}