package uk.co.sw.gifeline.data.breed

import uk.co.sw.gifeline.data.common.Repository
import uk.co.sw.gifeline.data.common.response.ApiResponse
import javax.inject.Inject

class CatBreedRepository @Inject constructor(
    private val catBreedService: CatBreedService
) : Repository() {

    suspend fun getCatBreeds(): ApiResponse<List<CatBreedEntity>> {
        return handleResponse { catBreedService.getAllBreeds() }
    }

    suspend fun searchCatBreeds(searchTerm: String): ApiResponse<List<CatBreedEntity>> {
        return handleResponse { catBreedService.searchBreeds(searchTerm) }
    }

    suspend fun findCatBreed(breedId: String): ApiResponse<CatBreedEntity> {
        return handleResponse { catBreedService.findBreed(breedId) }
    }

}