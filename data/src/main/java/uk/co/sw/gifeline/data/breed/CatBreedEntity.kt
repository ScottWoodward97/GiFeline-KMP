package uk.co.sw.gifeline.data.breed

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatBreedEntity(
    val id: String,
    val name: String,
    @Json(name = "alt_names") val altNames: String?,
    val origin: String,
    val description: String,
    @Json(name = "life_span") val lifeSpan: String,
    val weight: Weight,
    @Json(name = "energy_level") val energyLevel: Int,
    val intelligence: Int,
    val vocalisation: Int,
    @Json(name = "wikipedia_url") val wikiUrl: String?,
) {
    @JsonClass(generateAdapter = true)
    data class Weight(
        val imperial: String,
        val metric: String,
    )
}
