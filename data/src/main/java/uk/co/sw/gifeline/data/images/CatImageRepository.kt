package uk.co.sw.gifeline.data.images

import uk.co.sw.gifeline.data.common.KtorRepository
import uk.co.sw.gifeline.data.common.response.ApiResponse
import javax.inject.Inject

class CatImageRepository @Inject constructor(
    private val catImagesService: CatImagesService,
) : KtorRepository() {

    suspend fun getCatImages(
        breedId: String,
        page: Int,
        limit: Int,
    ): ApiResponse<List<CatImagesEntity>> {
        return handleResponse { catImagesService.getImages(breedId, page, limit) }
    }

}