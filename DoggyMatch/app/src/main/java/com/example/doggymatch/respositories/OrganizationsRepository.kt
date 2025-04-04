package com.example.doggymatch.respositories

import com.example.doggymatch.daos.OrganizationsDao
import com.example.doggymatch.models.Organizations

class OrganizationsRepository(
    private val organizationsDao: OrganizationsDao
) {
    suspend fun getAllOrganizations(): List<Organizations> {
        return organizationsDao.getAllOrganizations()
    }
    suspend fun getOrganizationById(id: Int): Organizations? {
        return organizationsDao.getOrganizationById(id)
    }
    suspend fun insertOrganization(organization: Organizations) {
        organizationsDao.insertOrganization(organization)
    }
    suspend fun updateOrganization(organization: Organizations) {
        organizationsDao.updateOrganization(organization)
    }
    suspend fun deleteOrganization(organization: Organizations) {
        organizationsDao.deleteOrganization(organization)
    }

}