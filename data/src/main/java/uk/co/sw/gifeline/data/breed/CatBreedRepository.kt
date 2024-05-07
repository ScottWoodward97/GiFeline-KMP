package uk.co.sw.gifeline.data.breed

import retrofit2.HttpException
import uk.co.sw.gifeline.data.common.response.ApiResponse
import javax.inject.Inject

class CatBreedRepository @Inject constructor(
    private val catBreedService: CatBreedService
) {

    suspend fun getCatBreeds(): ApiResponse<List<CatBreedEntity>> {
        return try {
            val response = catBreedService.getBreeds()
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

}