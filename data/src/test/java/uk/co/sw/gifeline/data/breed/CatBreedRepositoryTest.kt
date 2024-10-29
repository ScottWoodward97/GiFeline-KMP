package uk.co.sw.gifeline.data.breed

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

class CatBreedRepositoryTest {

    private val mockCatBreedService: CatBreedService = mockk()

    private lateinit var repository: CatBreedRepository

    @Before
    fun setUp() {
        repository = CatBreedRepository(catBreedService = mockCatBreedService)
    }

    @After
    fun tearDown() {
        confirmVerified(mockCatBreedService)
    }

    @Test
    fun `Given successful response and body, When get breeds, Then return success`() = runTest {
        // Given
        val mockEntity: CatBreedEntity = mockk()
        val mockStatus: HttpStatusCode = mockk {
            every { value } returns 200
        }
        val mockResponse: HttpResponse = mockk {
            every { status } returns mockStatus
            coEvery { body<List<CatBreedEntity>>() } returns listOf(mockEntity)
        }
        coEvery { mockCatBreedService.getAllBreeds() } returns mockResponse

        // When
        val result = repository.getCatBreeds()

        // Then
        coVerify { mockCatBreedService.getAllBreeds() }
        assertThat(result).isInstanceOf(ApiResponse.Success::class.java)
        with(result as ApiResponse.Success<List<CatBreedEntity>>) {
            assertThat(data).containsExactly(mockEntity)
        }
    }

    @Test
    fun `Given unsuccessful response, When get breeds, Then return error`() = runTest {
        // Given
        mockkStatic(HttpResponse::bodyAsText)
        val mockStatus: HttpStatusCode = mockk {
            every { value } returns 500
        }
        val mockResponse: HttpResponse = mockk {
            every { status } returns mockStatus
            coEvery { body<List<CatBreedEntity>>() } returns mockk()
            coEvery { bodyAsText() } returns "message"
        }
        coEvery { mockCatBreedService.getAllBreeds() } returns mockResponse

        // When
        val result = repository.getCatBreeds()

        // Then
        coVerify { mockCatBreedService.getAllBreeds() }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatBreedEntity>>) {
            assertThat(error).isInstanceOf(ResponseException::class.java)
            assertThat((error as ResponseException).message).contains("message")
        }
    }

    @Test
    fun `Given service error, When get breeds, Then return error`() = runTest {
        // Given
        val mockError: Exception = mockk()
        coEvery { mockCatBreedService.getAllBreeds() } throws mockError

        // When
        val result = repository.getCatBreeds()

        // Then
        coVerify { mockCatBreedService.getAllBreeds() }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatBreedEntity>>) {
            assertThat(error).isEqualTo(mockError)
        }
    }

    @Test
    fun `Given successful response and body, When search breeds, Then return success`() = runTest {
        // Given
        val mockEntity: CatBreedEntity = mockk()
        val mockStatus: HttpStatusCode = mockk {
            every { value } returns 200
        }
        val mockResponse: HttpResponse = mockk {
            every { status } returns mockStatus
            coEvery { body<List<CatBreedEntity>>() } returns listOf(mockEntity)
        }
        coEvery { mockCatBreedService.searchBreeds("term") } returns mockResponse

        // When
        val result = repository.searchCatBreeds("term")

        // Then
        coVerify { mockCatBreedService.searchBreeds("term") }
        assertThat(result).isInstanceOf(ApiResponse.Success::class.java)
        with(result as ApiResponse.Success<List<CatBreedEntity>>) {
            assertThat(data).containsExactly(mockEntity)
        }
    }

    @Test
    fun `Given unsuccessful response, When search breeds, Then return error`() = runTest {
        // Given
        mockkStatic(HttpResponse::bodyAsText)
        val mockStatus: HttpStatusCode = mockk {
            every { value } returns 404
        }
        val mockResponse: HttpResponse = mockk {
            every { status } returns mockStatus
            coEvery { body<List<CatBreedEntity>>() } returns mockk()
            coEvery { bodyAsText() } returns "message"
        }
        coEvery { mockCatBreedService.searchBreeds("term") } returns mockResponse

        // When
        val result = repository.searchCatBreeds("term")

        // Then
        coVerify { mockCatBreedService.searchBreeds("term") }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatBreedEntity>>) {
            assertThat(error).isInstanceOf(ResponseException::class.java)
            assertThat((error as ResponseException).message).contains("message")
        }
    }

    @Test
    fun `Given service error, When search breeds, Then return error`() = runTest {
        // Given
        val mockError: Exception = mockk()
        coEvery { mockCatBreedService.searchBreeds("term") } throws mockError

        // When
        val result = repository.searchCatBreeds("term")

        // Then
        coVerify { mockCatBreedService.searchBreeds("term") }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatBreedEntity>>) {
            assertThat(error).isEqualTo(mockError)
        }
    }

}