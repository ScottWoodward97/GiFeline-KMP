package uk.co.sw.gifeline.feature.breedprofile.viewstate

import uk.co.sw.gifeline.domain.breed.CatBreed
import uk.co.sw.gifeline.domain.images.CatImage
import javax.inject.Inject

class BreedProfileViewDataMapper @Inject constructor() {

    private companion object {
        const val STAT_MAX: Int = 5
    }

    fun map(breed: CatBreed, images: List<CatImage>): BreedProfileViewState.Profile {
        return BreedProfileViewState.Profile(
            id = breed.id,
            imageUrls = images.map { it.url },
            name = breed.name,
            altNames = breed.altNames,
            origin = breed.origin,
            description = breed.description,
            lifeSpan = breed.lifeSpan.filterNot { it.isWhitespace() },
            weight = breed.weight.filterNot { it.isWhitespace() },
            stats = with(breed.stats){
                listOf(
                    BreedProfileViewState.Profile.Stat("Energy", energyLevel, STAT_MAX),
                    BreedProfileViewState.Profile.Stat("Intelligence", intelligence, STAT_MAX),
                    BreedProfileViewState.Profile.Stat("Vocalisation", vocalisation, STAT_MAX),
                )
            },
            wikiUrl = breed.wikiUrl,
        )
    }
}