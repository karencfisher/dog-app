package com.example.doggymatch

import android.app.Application
import androidx.room.Room
import com.example.doggymatch.respositories.DogsBreedsDescriptionsRepository
import com.example.doggymatch.respositories.DogsBreedsSelectorsRepository
import com.example.doggymatch.respositories.OrganizationsRepository
import com.example.doggymatch.respositories.SelectedDogsRepository


class DoggyMatchApplication: Application() {
    private val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "doggy-match-db"
        )
            .createFromAsset("database/doggy.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    val dogBreedsDescriptionsRepository by lazy {
        DogsBreedsDescriptionsRepository(
            db.dogBreedsDescriptionsDao
        )
    }
    val dogBreedsSelectorsRepository by lazy {
        DogsBreedsSelectorsRepository(
            db.dogBreedsSelectorsDao
        )
    }
    val organizationsRepository by lazy {
        OrganizationsRepository(
            db.organizationsDao
        )
    }
    val selectedDogsRepository by lazy {
        SelectedDogsRepository(
            db.selectedDogsDao
        )
    }
}