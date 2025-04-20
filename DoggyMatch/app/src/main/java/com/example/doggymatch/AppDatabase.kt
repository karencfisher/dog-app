package com.example.doggymatch

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.doggymatch.daos.DogsBreedsDescriptionsDao
import com.example.doggymatch.daos.DogsBreedsSelectorsDao
import com.example.doggymatch.daos.OrganizationsDao
import com.example.doggymatch.daos.SelectedDogsDao
import com.example.doggymatch.models.DogBreedsDescriptions
import com.example.doggymatch.models.DogBreedsSelectors
import com.example.doggymatch.models.Organizations
import com.example.doggymatch.models.SelectedDogs

@Database(entities = [
    DogBreedsDescriptions::class,
    DogBreedsSelectors::class,
    Organizations::class,
    SelectedDogs::class
], version = 10)
abstract class AppDatabase: RoomDatabase() {
    abstract val dogBreedsDescriptionsDao: DogsBreedsDescriptionsDao
    abstract val dogBreedsSelectorsDao: DogsBreedsSelectorsDao
    abstract val organizationsDao: OrganizationsDao
    abstract val selectedDogsDao: SelectedDogsDao
}