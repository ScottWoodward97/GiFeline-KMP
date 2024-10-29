package uk.co.sw.gifeline.data.images

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import uk.co.sw.gifeline.data.common.di.CatKtor
import javax.inject.Inject

class CatImagesServiceImpl @Inject constructor(
    @CatKtor private val client: HttpClient,
) : CatImagesService {

    override suspend fun getImages(
        breedId: String,
        page: Int,
        limit: Int,
        order: String
    ): HttpResponse {
        return client.get("v1/images/search") {
            parameter("breed_ids", breedId)
            parameter("page", page)
            parameter("limit", limit)
            parameter("order", order)
        }
    }
}