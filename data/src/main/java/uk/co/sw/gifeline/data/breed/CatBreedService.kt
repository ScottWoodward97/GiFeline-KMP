package uk.co.sw.gifeline.data.breed

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatBreedService {

    @GET("v1/breeds")
    suspend fun getAllBreeds(): Response<List<CatBreedEntity>>

    @GET("v1/breeds/search")
    suspend fun searchBreeds(
        @Query("q") searchTerm: String,
    ): Response<List<CatBreedEntity>>

    @GET("v1/breeds/{breedId}")
    suspend fun findBreed(
        @Path("breedId") searchTerm: String,
    ): Response<CatBreedEntity>

}