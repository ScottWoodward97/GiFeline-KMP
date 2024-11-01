package uk.co.sw.gifeline.domain.images

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import uk.co.sw.gifeline.data.common.response.ApiResponse
import uk.co.sw.gifeline.data.images.CatImageRepository
import uk.co.sw.gifeline.data.images.CatImagesEntity
import uk.co.sw.gifeline.domain.response.Result

class GetCatImagesUseCaseTest {

    private val mockCatImageRepository: CatImageRepository = mockk()
    private val mockCatImageMapper: CatImageMapper = mockk()

    private lateinit var useCase: GetCatImagesUseCase

    @Before
    fun setUp() {
        useCase = GetCatImagesUseCase(
            catImageRepository = mockCatImageRepository,
            catImageMapper = mockCatImageMapper
        )
    }

    @After
    fun tearDown() {
        confirmVerified(
            mockCatImageRepository,
            mockCatImageMapper,
        )
    }

    @Test
    fun `Given repository success, When invoked, Then return success result`() = runTest {
        // Given
        val breedId = "breedId"
        val page = 0
        val limit = 10
        val mockEntity: CatImagesEntity = mockk()
        coEvery {
            mockCatImageRepository.getCatImages(breedId, page, limit)
        } returns ApiResponse.Success(listOf(mockEntity))

        val mockImage: CatImage = mockk()
        every {
            mockCatImageMapper.map(mockEntity)
        } returns mockImage

        // When
        val result = useCase(breedId, page, limit)

        // Then
        coVerify { mockCatImageRepository.getCatImages(breedId, page, limit) }
        verify { mockCatImageMapper.map(mockEntity) }
        assertThat(result).isInstanceOf(Result.Success::class.java)
        with(result as Result.Success<List<CatImage>>) {
            assertThat(data).containsExactly(mockImage)
        }
    }

    @Test
    fun `Given repository error, When invoked, Then return error result`() = runTest {
        // Given
        val breedId = "breedId"
        val page = 0
        val limit = 10
        val mockError: Throwable = mockk()
        coEvery {
            mockCatImageRepository.getCatImages(breedId, page, limit)
        } returns ApiResponse.Error(mockError)

        // When
        val result = useCase(breedId, page, limit)

        // Then
        coVerify { mockCatImageRepository.getCatImages(breedId, page, limit) }
        assertThat(result).isInstanceOf(Result.Error::class.java)
        with(result as Result.Error) {
            assertThat(error).isEqualTo(mockError)
        }
    }

}