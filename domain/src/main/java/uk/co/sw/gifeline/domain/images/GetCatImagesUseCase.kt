package uk.co.sw.gifeline.domain.images

import uk.co.sw.gifeline.data.common.response.ApiResponse
import uk.co.sw.gifeline.data.images.CatImageRepository
import uk.co.sw.gifeline.domain.response.Result
import javax.inject.Inject

class GetCatImagesUseCase @Inject constructor(
    private val catImageRepository: CatImageRepository,
    private val catImageMapper: CatImageMapper,
) {
    suspend operator fun invoke(breedId: String): Result<List<CatImage>> {
        return when (val result = catImageRepository.getCatImages(breedId)) {
            is ApiResponse.Success -> Result.Success(result.data.map { catImageMapper.map(it) })
            is ApiResponse.Error -> Result.Error(result.error)
        }
    }
}