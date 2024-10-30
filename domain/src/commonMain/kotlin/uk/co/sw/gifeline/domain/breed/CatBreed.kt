package uk.co.sw.gifeline.domain.breed

data class CatBreed(
    val id: String,
    val name: String,
    val altNames: List<String>,
    val origin: String,
    val description: String,
    val lifeSpan: String,
    val weight: String,
    val stats: Stats,
    val wikiUrl: String?,
) {
    data class Stats(
        val energyLevel: Int,
        val intelligence: Int,
        val vocalisation: Int,
    )
}
