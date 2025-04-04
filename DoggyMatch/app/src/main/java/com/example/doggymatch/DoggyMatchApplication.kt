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
        ).createFromAsset("database/doggy.db").build()
    }
    val dogBreedsDescriptionsDao by lazy {
        DogsBreedsDescriptionsRepository(
            db.dogBreedsDescriptionsDao
        )
    }
    val dogBreedsSelectorsDao by lazy {
        DogsBreedsSelectorsRepository(
            db.dogBreedsSelectorsDao
        )
    }
    val organizationsDao by lazy {
        OrganizationsRepository(
            db.organizationsDao
        )
    }
    val selectedDogsDao by lazy {
        SelectedDogsRepository(
            db.selectedDogsDao
        )
    }
}