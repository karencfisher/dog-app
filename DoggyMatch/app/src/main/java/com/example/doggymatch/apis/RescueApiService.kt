package com.example.doggymatch.apis

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RescueApiService {
    @POST("v5/public/animals/search/available")
    fun searchAnimals(@Body body: RescueRequestBody): Call<AnimalResponse>

    @GET("v5/public/orgs/{orgId.toString()}")
    fun getOrgDetails(@Body orgId: Int): Call<OrgDetailsResponse>
}

data class DataWrapper(
    val filters: List<Filter>,
    val filterProcessing: String,
    val filterRadius: FilterRadius
)

data class Filter(
    val fieldName: String,
    val operation: String,
    val criteria: String
)

data class FilterRadius(
    val miles: Int,
    val postalCode: String
)

data class RescueRequestBody(
    val data: DataWrapper
)

data class AnimalResponse(
    val meta: List<Any>,
    val data: List<Any>,
    val included: List<Any>
)

data class OrgDetailsResponse(
    val data: List<Any>,
    val meta: List<Any>
)