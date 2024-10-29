package uk.co.sw.gifeline.data.images

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import uk.co.sw.gifeline.data.common.response.ApiResponse

class CatImageRepositoryTest {

    private val mockCatImageService: CatImagesService = mockk()

    private lateinit var repository: CatImageRepository

    @Before
    fun setUp() {
        repository = CatImageRepository(catImagesService = mockCatImageService)
    }

    @After
    fun tearDown() {
        confirmVerified(mockCatImageService)
    }

    @Test
    fun `Given successful response and body, When get images, Then return success`() = runTest {
        // Given
        val breedId = "breedId"
        val page = 0
        val limit = 1
        val mockEntity: CatImagesEntity = mockk()
        val mockStatus: HttpStatusCode = mockk {
            every { value } returns 200
        }
        val mockResponse: HttpResponse = mockk {
            every { status } returns mockStatus
            coEvery { body<List<CatImagesEntity>>() } returns listOf(mockEntity)
        }
        coEvery { mockCatImageService.getImages(breedId, any(), any()) } returns mockResponse

        // When
        val result = repository.getCatImages(breedId, page, limit)

        // Then
        coVerify { mockCatImageService.getImages(breedId, page, limit) }
        assertThat(result).isInstanceOf(ApiResponse.Success::class.java)
        with(result as ApiResponse.Success<List<CatImagesEntity>>) {
            assertThat(data).containsExactly(mockEntity)
        }
    }

    @Test
    fun `Given unsuccessful response, When get images, Then return error`() = runTest {
        // Given
        mockkStatic(HttpResponse::bodyAsText)
        val breedId = "breedId"
        val page = 0
        val limit = 1
        val mockStatus: HttpStatusCode = mockk {
            every { value } returns 500
        }
        val mockResponse: HttpResponse = mockk {
            every { status } returns mockStatus
            coEvery { body<List<CatImagesEntity>>() } returns mockk()
            coEvery { bodyAsText() } returns "message"
        }
        coEvery { mockCatImageService.getImages(breedId, page, limit) } returns mockResponse

        // When
        val result = repository.getCatImages(breedId, page, limit)

        // Then
        coVerify { mockCatImageService.getImages(breedId, page, limit) }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatImagesEntity>>) {
            assertThat(error).isInstanceOf(ResponseException::class.java)
            assertThat((error as ResponseException).message).contains("message")
        }
    }

    @Test
    fun `Given service error, When get images, Then return error`() = runTest {
        // Given
        val breedId = "breedId"
        val page = 0
        val limit = 1
        val mockError: Exception = mockk()
        coEvery { mockCatImageService.getImages(breedId, page, limit) } throws mockError

        // When
        val result = repository.getCatImages(breedId, page, limit)

        // Then
        coVerify { mockCatImageService.getImages(breedId, page, limit) }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatImagesEntity>>) {
            assertThat(error).isEqualTo(mockError)
        }
    }

}