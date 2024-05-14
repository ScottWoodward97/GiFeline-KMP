package uk.co.sw.gifeline.data.images

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
        val mockResponse: Response<List<CatImagesEntity>> = mockk {
            every { isSuccessful } returns true
            every { body() } returns listOf(mockEntity)
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
    fun `Given successful response and null body, When get images, Then return error`() = runTest {
        // Given
        val breedId = "breedId"
        val page = 0
        val limit = 1
        val mockResponse: Response<List<CatImagesEntity>> = mockk {
            every { isSuccessful } returns true
            every { body() } returns null
            every { code() } returns 404
            every { message() } returns "message"
        }
        coEvery { mockCatImageService.getImages(breedId, page, limit) } returns mockResponse

        // When
        val result = repository.getCatImages(breedId, page, limit)

        // Then
        coVerify { mockCatImageService.getImages(breedId, page, limit) }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatImagesEntity>>) {
            assertThat(error).isInstanceOf(HttpException::class.java)
            assertThat((error as HttpException).response()).isEqualTo(mockResponse)
        }
    }

    @Test
    fun `Given unsuccessful response, When get images, Then return error`() = runTest {
        // Given
        val breedId = "breedId"
        val page = 0
        val limit = 1
        val mockResponse: Response<List<CatImagesEntity>> = mockk {
            every { isSuccessful } returns false
            every { body() } returns null
            every { code() } returns 500
            every { message() } returns "message"
        }
        coEvery { mockCatImageService.getImages(breedId, page, limit) } returns mockResponse

        // When
        val result = repository.getCatImages(breedId, page, limit)

        // Then
        coVerify { mockCatImageService.getImages(breedId, page, limit) }
        assertThat(result).isInstanceOf(ApiResponse.Error::class.java)
        with(result as ApiResponse.Error<List<CatImagesEntity>>) {
            assertThat(error).isInstanceOf(HttpException::class.java)
            assertThat((error as HttpException).response()).isEqualTo(mockResponse)
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