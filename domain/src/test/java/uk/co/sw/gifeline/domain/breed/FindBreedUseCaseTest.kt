package uk.co.sw.gifeline.domain.breed

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
import uk.co.sw.gifeline.data.breed.CatBreedEntity
import uk.co.sw.gifeline.data.breed.CatBreedRepository
import uk.co.sw.gifeline.data.common.response.ApiResponse
import uk.co.sw.gifeline.domain.response.Result

class FindBreedUseCaseTest {
    private val mockCatBreedRepository: CatBreedRepository = mockk()
    private val mockCatBreedMapper: CatBreedMapper = mockk()

    private lateinit var useCase: FindBreedUseCase

    @Before
    fun setUp() {
        useCase = FindBreedUseCase(
            catBreedRepository = mockCatBreedRepository,
            catBreedMapper = mockCatBreedMapper
        )
    }

    @After
    fun tearDown() {
        confirmVerified(mockCatBreedRepository, mockCatBreedMapper)
    }

    @Test
    fun `Given breed success, When find breed, Then return mapped success`() = runTest {
        // Given
        val breedId = "breedId"
        val mockEntity: CatBreedEntity = mockk()
        coEvery {
            mockCatBreedRepository.findCatBreed(breedId)
        } returns ApiResponse.Success(mockEntity)

        val mockBreed: CatBreed = mockk()
        every { mockCatBreedMapper.map(mockEntity) } returns mockBreed

        // When
        val result = useCase.invoke(breedId)

        // Then
        assertThat(result).isInstanceOf(Result.Success::class.java)
        assertThat((result as Result.Success<CatBreed>).data).isEqualTo(mockBreed)
        coVerify { mockCatBreedRepository.findCatBreed(breedId) }
        verify { mockCatBreedMapper.map(mockEntity) }
    }

    @Test
    fun `Given breed error, When find breed, Then return mapped success`() = runTest {
        // Given
        val breedId = "breedId"
        val mockError: Throwable = mockk()
        coEvery {
            mockCatBreedRepository.findCatBreed(breedId)
        } returns ApiResponse.Error(mockError)

        // When
        val result = useCase.invoke(breedId)

        // Then
        assertThat(result).isInstanceOf(Result.Error::class.java)
        assertThat((result as Result.Error).error).isEqualTo(mockError)
        coVerify { mockCatBreedRepository.findCatBreed(breedId) }
    }
}