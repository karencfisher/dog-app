package com.example.doggymatch.apis

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RescueApiService {
    @POST("v5/public/animals/search/available")
    suspend fun searchAnimals(@Body body: RescueRequestBody): AnimalResponse

    @GET("v5/public/orgs/{orgId.toString()}")
    suspend fun getOrgDetails(@Body orgId: Int): OrgDetailsResponse
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
    @SerializedName("data") val data: DataWrapper
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