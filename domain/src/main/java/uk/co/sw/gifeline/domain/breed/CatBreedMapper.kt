package uk.co.sw.gifeline.domain.breed

import uk.co.sw.gifeline.data.breed.CatBreedEntity
import javax.inject.Inject

class CatBreedMapper @Inject constructor() {

    fun map(entity: CatBreedEntity): CatBreed {
        return CatBreed(
            id = entity.id,
            name = entity.name,
            altNames = entity.altNames?.split(", ").orEmpty()
                .filter { it.isNotBlank() },
            origin = entity.origin,
            description = entity.description,
            lifeSpan = entity.lifeSpan,
            weight = entity.weight.metric,
            stats = CatBreed.Stats(
                energyLevel = entity.energyLevel,
                intelligence = entity.intelligence,
                vocalisation = entity.vocalisation
            ),
            wikiUrl = entity.wikiUrl,
        )
    }

}