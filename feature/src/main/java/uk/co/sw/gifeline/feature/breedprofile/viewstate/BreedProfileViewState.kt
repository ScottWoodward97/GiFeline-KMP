package uk.co.sw.gifeline.feature.breedprofile.viewstate

sealed class BreedProfileViewState {
    data class Profile(
        val id: String,
        val imageUrls: List<String>,
        val name: String,
        val altNames: List<String>,
        val origin: String,
        val description: String,
        val lifeSpan: String,
        val weight: String,
        val stats: List<Stat>,
        val wikiUrl: String?,
    ) : BreedProfileViewState() {
        data class Stat(
            val name: String,
            val score: Int,
            val max: Int,
        )
    }

    data object Error : BreedProfileViewState()

    data object Loading : BreedProfileViewState()
}