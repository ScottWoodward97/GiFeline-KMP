package uk.co.sw.gifeline.domain.breed

import uk.co.sw.gifeline.data.breed.CatBreedRepository
import uk.co.sw.gifeline.data.common.response.ApiResponse
import uk.co.sw.gifeline.domain.response.Result
import javax.inject.Inject

class FindBreedUseCase @Inject constructor(
    private val catBreedRepository: CatBreedRepository,
    private val catBreedMapper: CatBreedMapper,
) {
    suspend operator fun invoke(breedId: String): Result<CatBreed> {
        return when (val result = catBreedRepository.findCatBreed(breedId)) {
            is ApiResponse.Success -> Result.Success(catBreedMapper.map(result.data))
            is ApiResponse.Error -> Result.Error(result.error)
        }
    }
}