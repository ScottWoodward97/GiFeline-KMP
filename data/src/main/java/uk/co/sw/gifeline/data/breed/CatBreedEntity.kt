package uk.co.sw.gifeline.data.breed

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatBreedEntity(
    val id: String,
    val name: String,
    @Json(name = "alt_names") val altNames: String?,
)
