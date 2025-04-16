package com.example.doggymatch.models

// models for GSON parsing of the API response
data class Organization (
    val attributes: OrganizationAttributes,
    val id: String
)

data class OrganizationAttributes (
    val about: String?,
    val adoptionProcess: String?,
    val city: String?,
    val email: String?,
    val facebookUrl: String?,
    val lat: Float?,
    val lon: Float?,
    val name: String?,
    val phone: String?,
    val postalcode: String?,
    val state: String?,
    val street: String?,
    val url: String?
)