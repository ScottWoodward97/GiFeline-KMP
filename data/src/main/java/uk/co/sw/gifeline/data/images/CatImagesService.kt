package uk.co.sw.gifeline.data.images

import io.ktor.client.statement.HttpResponse

interface CatImagesService {

    suspend fun getImages(
        breedId: String,
        page: Int,
        limit: Int,
        order: String = "ASC",
    ): HttpResponse

}