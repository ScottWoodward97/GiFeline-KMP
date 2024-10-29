package uk.co.sw.gifeline.feature.breedprofile.viewstate

import gifeline.feature.generated.resources.Res
import gifeline.feature.generated.resources.breed_profile_state_energy_title
import gifeline.feature.generated.resources.breed_profile_state_intelligence_title
import gifeline.feature.generated.resources.breed_profile_state_vocalisation_title
import uk.co.sw.gifeline.domain.breed.CatBreed
import uk.co.sw.gifeline.domain.images.CatImage
import uk.co.sw.gifeline.feature.common.ResourceStringProvider

class BreedProfileViewDataMapper(
    private val resources: ResourceStringProvider,
) {

    private companion object {
        const val STAT_MAX: Int = 5
    }

    suspend fun map(breed: CatBreed, images: List<CatImage>): BreedProfileViewState.Profile {
        return BreedProfileViewState.Profile(
            id = breed.id,
            imageUrls = images.map { it.url },
            name = breed.name,
            altNames = breed.altNames,
            origin = breed.origin,
            description = breed.description,
            lifeSpan = breed.lifeSpan.filterNot { it.isWhitespace() },
            weight = breed.weight.filterNot { it.isWhitespace() },
            stats = with(breed.stats) {
                listOf(
                    BreedProfileViewState.Profile.Stat(
                        name = resources.getResourceString(Res.string.breed_profile_state_energy_title),
                        score = energyLevel,
                        max = STAT_MAX
                    ),
                    BreedProfileViewState.Profile.Stat(
                        name = resources.getResourceString(Res.string.breed_profile_state_intelligence_title),
                        score = intelligence,
                        max = STAT_MAX
                    ),
                    BreedProfileViewState.Profile.Stat(
                        name = resources.getResourceString(Res.string.breed_profile_state_vocalisation_title),
                        score = vocalisation,
                        max = STAT_MAX
                    ),
                )
            },
            wikiUrl = breed.wikiUrl,
        )
    }
}