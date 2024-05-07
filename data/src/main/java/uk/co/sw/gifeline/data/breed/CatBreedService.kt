package uk.co.sw.gifeline.data.breed

import retrofit2.Response
import retrofit2.http.GET

interface CatBreedService {

    @GET("v1/breeds")
    suspend fun getBreeds(): Response<List<CatBreedEntity>>

}