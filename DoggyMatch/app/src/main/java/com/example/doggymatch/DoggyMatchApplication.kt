package com.example.doggymatch

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.doggymatch.respositories.DogsBreedsDescriptionsRepository
import com.example.doggymatch.respositories.DogsBreedsSelectorsRepository
import com.example.doggymatch.respositories.OrganizationsRepository
import com.example.doggymatch.respositories.SelectedDogsRepository
import org.osmdroid.config.Configuration


class DoggyMatchApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize osmdroid configuration
        val appName = getString(R.string.app_name)
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        val userAgentValue = "$appName/${packageInfo.versionName} (${packageName}; Android)"

        Configuration.getInstance().load(
            applicationContext,
            getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        )
        Configuration.getInstance().userAgentValue = userAgentValue
    }

    private val db by lazy {
        val dbFile = getDatabasePath("doggy-match-db")
        val dbExists = dbFile.exists()

        Room.databaseBuilder(
                this,
                AppDatabase::class.java,
                "doggy-match-db"
            )
            .apply {
                if (!dbExists) {
                    createFromAsset("database/doggy.db")
                }
            }
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