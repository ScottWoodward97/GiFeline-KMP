package uk.co.sw.gifeline.data.breed

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import uk.co.sw.gifeline.data.common.di.CatKtor
import javax.inject.Inject

class CatBreedServiceImpl @Inject constructor(
    @CatKtor private val client: HttpClient,
) : CatBreedService {
    override suspend fun getAllBreeds(): HttpResponse {
        return client.get("v1/breeds")
    }

    override suspend fun searchBreeds(searchTerm: String): HttpResponse {
        return client.get("v1/breeds/search") {
            parameter("q", searchTerm)
        }
    }

    override suspend fun findBreed(breedId: String): HttpResponse {
        return client.get("v1/breeds/${breedId}")
    }
}