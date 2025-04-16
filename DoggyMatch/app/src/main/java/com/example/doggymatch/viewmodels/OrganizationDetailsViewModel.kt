package com.example.doggymatch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.doggymatch.DoggyMatchApplication
import com.example.doggymatch.datasources.RetrofitClient
import com.example.doggymatch.models.Organization
import com.example.doggymatch.models.Organizations
import com.example.doggymatch.respositories.OrganizationsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrganizationDetailsViewModel (
    private val orgId: Int,
    private val organizationsRepository : OrganizationsRepository,
): ViewModel() {
    private var _organization = MutableStateFlow<Organizations?>(null)
    val organization: StateFlow<Organizations?> = _organization
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private var _orgInfo: List<Organization> = emptyList()


    init {
        loadOrganizationDetails()
    }

    private fun loadOrganizationDetails() {
        viewModelScope.launch {
            _organization.value = organizationsRepository.getOrganizationById(orgId)
            if (_organization.value == null) {
                try {
                    val response = RetrofitClient.api.getOrgDetails(orgId)
                    _orgInfo = response.data
                    makeOrganization()
                    if (_organization.value == null) {
                        _error.value = "Organization not found"
                    } else {
                        organizationsRepository.insertOrganization(_organization.value!!)
                    }
                } catch (e: Exception) {
                    _error.value = "Network error: ${e.message}"
                } finally {
                    _isLoading.value = false
                }

            }
        }
    }

    private fun makeOrganization() {
        for (org in _orgInfo) {
            _organization.value = Organizations(
                id = org.id.toIntOrNull() ?: 0,
                name = org.attributes.name ?: "",
                about = org.attributes.about ?: "",
                adoptionProcess = org.attributes.adoptionProcess ?: "",
                address = org.attributes.street ?: "",
                city = org.attributes.city ?: "",
                state = org.attributes.state ?: "",
                zip = org.attributes.postalcode ?: "",
                phone = org.attributes.phone ?: "",
                latitude = org.attributes.lat ?: 0f,
                longitude = org.attributes.lon ?: 0f,
                email = org.attributes.email ?: "",
                website = org.attributes.url ?: "",
                facebook = org.attributes.facebookUrl ?: "",
            )
        }
    }

    companion object {
        val ORG_ID_KEY = object: CreationExtras.Key<Int> {}
        val Factory = viewModelFactory {
            initializer {
                // Try to get orgId from extras, but don't fail if missing
                val orgId = runCatching { this[ORG_ID_KEY] }.getOrNull() ?: 0
                val app = this[APPLICATION_KEY] as DoggyMatchApplication
                OrganizationDetailsViewModel(
                    orgId,
                    app.organizationsRepository
                )
            }
        }
    }
}