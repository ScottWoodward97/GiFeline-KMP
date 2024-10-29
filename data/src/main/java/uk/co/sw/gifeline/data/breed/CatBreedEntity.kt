package uk.co.sw.gifeline.data.breed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatBreedEntity(
    val id: String,
    val name: String,
    @SerialName("alt_names") val altNames: String?,
    val origin: String,
    val description: String,
    @SerialName("life_span") val lifeSpan: String,
    val weight: Weight,
    @SerialName("energy_level") val energyLevel: Int,
    val intelligence: Int,
    val vocalisation: Int,
    @SerialName("wikipedia_url") val wikiUrl: String?,
) {
    @Serializable
    data class Weight(
        val imperial: String,
        val metric: String,
    )
}
