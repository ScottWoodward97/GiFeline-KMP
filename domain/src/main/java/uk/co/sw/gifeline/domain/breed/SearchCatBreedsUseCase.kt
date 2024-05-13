package uk.co.sw.gifeline.domain.breed

import uk.co.sw.gifeline.data.breed.CatBreedRepository
import uk.co.sw.gifeline.data.common.response.ApiResponse
import uk.co.sw.gifeline.domain.response.Result
import javax.inject.Inject

class SearchCatBreedsUseCase @Inject constructor(
    private val catBreedRepository: CatBreedRepository,
    private val catBreedMapper: CatBreedMapper,
) {
    suspend operator fun invoke(searchTerm: String): Result<List<CatBreed>> {
        return when (val result = catBreedRepository.searchCatBreeds(searchTerm)) {
            is ApiResponse.Success -> Result.Success(result.data.map { catBreedMapper.map(it) })
            is ApiResponse.Error -> Result.Error(result.error)
        }
    }
}