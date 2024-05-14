package uk.co.sw.gifeline.data.images

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatImagesService {

    @GET("v1/images/search")
    suspend fun getImages(
        @Query("breed_ids") breedId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("order") order: String = "ASC",
    ): Response<List<CatImagesEntity>>

}