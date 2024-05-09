package uk.co.sw.gifeline.data.images

import retrofit2.HttpException
import uk.co.sw.gifeline.data.common.response.ApiResponse
import javax.inject.Inject

class CatImageRepository @Inject constructor(
    private val catImagesService: CatImagesService,
) {

    suspend fun getCatImages(
        breedId: String,
        page: Int = PAGE,
        limit: Int = LIMIT,
    ): ApiResponse<List<CatImagesEntity>> {
        return try {
            val response = catImagesService.getImages(breedId, page, limit)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                ApiResponse.Success(body)
            } else {
                ApiResponse.Error(HttpException(response))
            }
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }

    private companion object {
        const val PAGE = 0
        const val LIMIT = 10
    }

}