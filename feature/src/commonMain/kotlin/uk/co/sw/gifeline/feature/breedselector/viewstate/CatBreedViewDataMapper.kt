package uk.co.sw.gifeline.feature.breedselector.viewstate

import uk.co.sw.gifeline.domain.breed.CatBreed
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState.CatBreeds.CatBreedViewData

class CatBreedViewDataMapper {

    fun map(breed: CatBreed): CatBreedViewData {
        return CatBreedViewData(
            id = breed.id,
            name = breed.name,
            altNames = breed.altNames
        )
    }
}