package uk.co.sw.gifeline.domain.images

import uk.co.sw.gifeline.data.common.response.ApiResponse
import uk.co.sw.gifeline.data.images.CatImageRepository
import uk.co.sw.gifeline.data.images.CatImagesEntity
import uk.co.sw.gifeline.domain.response.Result
import javax.inject.Inject

class GetCatImagesUseCase @Inject constructor(
    private val catImageRepository: CatImageRepository,
    private val catImageMapper: CatImageMapper,
) {
    suspend operator fun invoke(breedId: String): Result<List<CatImage>> {
        return handleResult(catImageRepository.getCatImages(breedId))
    }

    suspend operator fun invoke(breedId: String, numImages: Int): Result<List<CatImage>> {
        return handleResult(catImageRepository.getCatImages(breedId, limit = numImages))
    }

    private fun handleResult(response: ApiResponse<List<CatImagesEntity>>): Result<List<CatImage>> {
        return when (response) {
            is ApiResponse.Success -> Result.Success(response.data.map { catImageMapper.map(it) })
            is ApiResponse.Error -> Result.Error(response.error)
        }
    }
}