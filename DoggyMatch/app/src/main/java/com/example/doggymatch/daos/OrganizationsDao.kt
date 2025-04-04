package com.example.doggymatch.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.doggymatch.models.Organizations

@Dao
abstract class OrganizationsDao {
     @Insert
     abstract suspend fun insertOrganization(organization: Organizations)

     @Update
     abstract suspend fun updateOrganization(organization: Organizations)

     @Delete
     abstract suspend fun deleteOrganization(organization: Organizations)

     @Query("SELECT * FROM organizations")
     abstract suspend fun getAllOrganizations(): List<Organizations>

     @Query("SELECT * FROM organizations WHERE id = :id")
     abstract suspend fun getOrganizationById(id: Int): Organizations?
}