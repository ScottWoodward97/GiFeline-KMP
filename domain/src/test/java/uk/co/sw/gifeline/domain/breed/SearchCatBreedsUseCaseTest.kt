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

class SearchCatBreedsUseCaseTest {

    private val mockCatBreedRepository: CatBreedRepository = mockk()
    private val mockCatBreedMapper: CatBreedMapper = mockk()

    private lateinit var useCase: SearchCatBreedsUseCase

    @Before
    fun setUp() {
        useCase = SearchCatBreedsUseCase(
            catBreedRepository = mockCatBreedRepository,
            catBreedMapper = mockCatBreedMapper
        )
    }

    @After
    fun tearDown() {
        confirmVerified(
            mockCatBreedRepository,
            mockCatBreedMapper,
        )
    }

    @Test
    fun `Given repository success, When invoked, Then return success result`() = runTest {
        // Given
        val mockEntity: CatBreedEntity = mockk()
        coEvery {
            mockCatBreedRepository.searchCatBreeds("term")
        } returns ApiResponse.Success(listOf(mockEntity))

        val mockBreed: CatBreed = mockk()
        every {
            mockCatBreedMapper.map(mockEntity)
        } returns mockBreed

        // When
        val result = useCase("term")

        // Then
        coVerify { mockCatBreedRepository.searchCatBreeds("term") }
        verify { mockCatBreedMapper.map(mockEntity) }
        assertThat(result).isInstanceOf(Result.Success::class.java)
        with(result as Result.Success<List<CatBreed>>) {
            assertThat(data).containsExactly(mockBreed)
        }
    }

    @Test
    fun `Given repository error, When invoked, Then return error result`() = runTest {
        // Given
        val mockError: Throwable = mockk()
        coEvery {
            mockCatBreedRepository.searchCatBreeds("term")
        } returns ApiResponse.Error(mockError)

        // When
        val result = useCase("term")

        // Then
        coVerify { mockCatBreedRepository.searchCatBreeds("term") }
        assertThat(result).isInstanceOf(Result.Error::class.java)
        with(result as Result.Error) {
            assertThat(error).isEqualTo(mockError)
        }
    }
}