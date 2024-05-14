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

    suspend operator fun invoke(
        breedId: String,
        page: Int = PAGE,
        limit: Int = LIMIT
    ): Result<List<CatImage>> {
        return handleResult(
            catImageRepository.getCatImages(
                breedId = breedId,
                page = page,
                limit = limit
            )
        )
    }

    private fun handleResult(response: ApiResponse<List<CatImagesEntity>>): Result<List<CatImage>> {
        return when (response) {
            is ApiResponse.Success -> Result.Success(response.data.map { catImageMapper.map(it) })
            is ApiResponse.Error -> Result.Error(response.error)
        }
    }

    private companion object {
        const val PAGE = 0
        const val LIMIT = 10
    }
}