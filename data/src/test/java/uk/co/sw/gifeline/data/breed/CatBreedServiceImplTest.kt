package uk.co.sw.gifeline.data.breed

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respondBadRequest
import io.ktor.client.engine.mock.respondOk
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Parameters
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.fail
import org.junit.Test

class CatBreedServiceImplTest {

    @Test
    fun `Given service, When getAllBreeds, Then call endpoint`() = runTest {
        // Given
        val mockEngine = createMockEngine(expectedPath = "/v1/breeds", response = "getAllBreeds")
        val mockClient = HttpClient(mockEngine)
        val service = CatBreedServiceImpl(mockClient)

        // When
        val result = service.getAllBreeds()

        // Then
        with(result) {
            assertThat(status.value).isEqualTo(200)
            assertThat(bodyAsText()).isEqualTo("getAllBreeds")
        }

    }

    @Test
    fun `Given service, When searchBreeds, Then call endpoint`() = runTest {
        // Given
        val searchTerm = "searchTerm"
        val mockEngine = createMockEngine(
            expectedPath = "/v1/breeds/search",
            expectedParameters = Parameters.build { append("q", searchTerm) },
            response = "searchBreeds"
        )
        val mockClient = HttpClient(mockEngine)
        val service = CatBreedServiceImpl(mockClient)

        // When
        val result = service.searchBreeds(searchTerm)

        // Then
        with(result) {
            assertThat(status.value).isEqualTo(200)
            assertThat(bodyAsText()).isEqualTo("searchBreeds")
        }

    }

    @Test
    fun `Given service, When findBreed, Then call endpoint`() = runTest {
        // Given
        val breedId = "breedId"
        val mockEngine =
            createMockEngine(expectedPath = "/v1/breeds/$breedId", response = "findBreed")
        val mockClient = HttpClient(mockEngine)
        val service = CatBreedServiceImpl(mockClient)

        // When
        val result = service.findBreed(breedId)

        // Then
        with(result) {
            assertThat(status.value).isEqualTo(200)
            assertThat(bodyAsText()).isEqualTo("findBreed")
        }

    }

    private fun createMockEngine(expectedPath: String, response: String): MockEngine {
        return MockEngine { request ->
            if (request.url.encodedPath == expectedPath) {
                respondOk(response)
            } else {
                fail("Incorrect request path called: ${request.url.encodedPath}")
                respondBadRequest()
            }
        }
    }

    private fun createMockEngine(
        expectedPath: String,
        expectedParameters: Parameters,
        response: String
    ): MockEngine {
        return MockEngine { request ->
            if (request.url.encodedPath == expectedPath && request.url.parameters.entries()
                    .containsAll(expectedParameters.entries())
            ) {
                respondOk(response)
            } else {
                fail("Incorrect request path called: ${request.url.encodedPath}\n" +
                        "With parameters: ${request.url.parameters}")
                respondBadRequest()
            }
        }
    }

}