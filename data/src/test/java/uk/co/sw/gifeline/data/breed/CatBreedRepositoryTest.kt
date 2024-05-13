package uk.co.sw.gifeline.data.breed

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
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
        val mockResponse: Response<List<CatBreedEntity>> = mockk {
            every { isSuccessful } returns true
            every { body() } returns listOf(mockEntity)
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
    fun `Given successful response and null body, When get breeds, Then return error`() = runTest {
        // Given
        val mockResponse: Response<List<CatBreedEntity>> = mockk {
            every { isSuccessful } returns true
            every { body() } returns null
            every { code() } returns 404
            every { message() } returns "message"
        }
        coEvery { mockCatBreedService.getAllBreeds() } returns mockResponse

        // When
        val result = repository.getCatBreeds()

        // Then
        coVerify { mockCatBreedService.getAllBreeds() }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatBreedEntity>>) {
            assertThat(error).isInstanceOf(HttpException::class.java)
            assertThat((error as HttpException).response()).isEqualTo(mockResponse)
        }
    }

    @Test
    fun `Given unsuccessful response, When get breeds, Then return error`() = runTest {
        // Given
        val mockResponse: Response<List<CatBreedEntity>> = mockk {
            every { isSuccessful } returns false
            every { body() } returns null
            every { code() } returns 500
            every { message() } returns "message"
        }
        coEvery { mockCatBreedService.getAllBreeds() } returns mockResponse

        // When
        val result = repository.getCatBreeds()

        // Then
        coVerify { mockCatBreedService.getAllBreeds() }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatBreedEntity>>) {
            assertThat(error).isInstanceOf(HttpException::class.java)
            assertThat((error as HttpException).response()).isEqualTo(mockResponse)
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
        val mockResponse: Response<List<CatBreedEntity>> = mockk {
            every { isSuccessful } returns true
            every { body() } returns listOf(mockEntity)
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
    fun `Given successful response and null body, When search breeds, Then return error`() =
        runTest {
            // Given
            val mockResponse: Response<List<CatBreedEntity>> = mockk {
                every { isSuccessful } returns true
                every { body() } returns null
                every { code() } returns 404
                every { message() } returns "message"
            }
            coEvery { mockCatBreedService.searchBreeds("term") } returns mockResponse

            // When
            val result = repository.searchCatBreeds("term")

            // Then
            coVerify { mockCatBreedService.searchBreeds("term") }
            assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
            with(result as ApiResponse.Error<List<CatBreedEntity>>) {
                assertThat(error).isInstanceOf(HttpException::class.java)
                assertThat((error as HttpException).response()).isEqualTo(mockResponse)
            }
        }

    @Test
    fun `Given unsuccessful response, When search breeds, Then return error`() = runTest {
        // Given
        val mockResponse: Response<List<CatBreedEntity>> = mockk {
            every { isSuccessful } returns false
            every { body() } returns null
            every { code() } returns 500
            every { message() } returns "message"
        }
        coEvery { mockCatBreedService.searchBreeds("term") } returns mockResponse

        // When
        val result = repository.searchCatBreeds("term")

        // Then
        coVerify { mockCatBreedService.searchBreeds("term") }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatBreedEntity>>) {
            assertThat(error).isInstanceOf(HttpException::class.java)
            assertThat((error as HttpException).response()).isEqualTo(mockResponse)
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