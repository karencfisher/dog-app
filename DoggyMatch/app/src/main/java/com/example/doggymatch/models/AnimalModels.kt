package com.example.doggymatch.models

import androidx.core.text.HtmlCompat

// Models for GSON parsing of the API response
data class Animal(
    val type: String,
    val id: String,
    val attributes: AnimalAttributes,
    val relationships: AnimalRelationships
)

data class AnimalAttributes(
    val name: String?,
    val distance: Double?,
    val breedString: String?,
    val ageString: String?,
    val sex: String?,
    val sizeGroup: String?,
    val descriptionText: String?,
    val pictureThumbnailUrl: String?
) {
    val cleanDescription: String
        get() = descriptionText?.let {
            HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
        } ?: "No description available"
    val largerThumbnailUrl: String?
        get() = pictureThumbnailUrl?.replace("width=100", "width=300")

}

data class AnimalRelationships(
    val orgs: OrgRelationship?,
    val pictures: PicturesRelationship?
)

data class OrgRelationship(
    val data: List<OrgReference>
)

data class OrgReference(
    val type: String,
    val id: String
)

data class PicturesRelationship(
    val data: List<PictureReference>
)

data class PictureReference(
    val type: String,
    val id: String
)
