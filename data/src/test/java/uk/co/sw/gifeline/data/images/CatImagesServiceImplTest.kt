package uk.co.sw.gifeline.data.images

import io.ktor.client.HttpClient
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Parameters
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import uk.co.sw.gifeline.data.MockEngineBuilder.createMockEngine

class CatImagesServiceImplTest {

    @Test
    fun `Given service, When getImages, Then call endpoint`() = runTest {
        // Given
        val breedId = "breedId"
        val page = 1
        val limit = 5
        val order = "order"
        val mockEngine = createMockEngine(
            expectedPath = "/v1/images/search",
            expectedParameters = Parameters.build {
                append("breed_ids", breedId)
                append("page", page.toString())
                append("limit", limit.toString())
                append("order", order)
            },
            response = "getImages"
        )
        val mockClient = HttpClient(mockEngine)
        val service = CatImagesServiceImpl(mockClient)

        // When
        val result = service.getImages(breedId, page, limit, order)

        // Then
        with(result) {
            assertThat(status.value).isEqualTo(200)
            assertThat(bodyAsText()).isEqualTo("getImages")
        }
    }

}