package uk.co.sw.gifeline.data.breed

import io.ktor.client.statement.HttpResponse

interface CatBreedService {

    suspend fun getAllBreeds(): HttpResponse

    suspend fun searchBreeds(searchTerm: String): HttpResponse

    suspend fun findBreed(breedId: String): HttpResponse

}