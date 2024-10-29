package uk.co.sw.gifeline.feature.breedprofile

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
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
import org.junit.Rule
import org.junit.Test
import uk.co.sw.gifeline.domain.breed.CatBreed
import uk.co.sw.gifeline.domain.breed.FindBreedUseCase
import uk.co.sw.gifeline.domain.images.CatImage
import uk.co.sw.gifeline.domain.images.GetCatImagesUseCase
import uk.co.sw.gifeline.domain.response.Result
import uk.co.sw.gifeline.feature.CoroutineTestRule
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewDataMapper
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewState

class BreedProfileViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockFindBreedUseCase: FindBreedUseCase = mockk()
    private val mockGetImageUseCase: GetCatImagesUseCase = mockk()
    private val mockBreedProfileViewDataMapper: BreedProfileViewDataMapper = mockk()
    private val mockSavedStateHandle: SavedStateHandle = mockk()


    private lateinit var viewModel: BreedProfileViewModel

    @Before
    fun setUp() {
        viewModel = BreedProfileViewModel(
            findBreedUseCase = mockFindBreedUseCase,
            getImageUseCase = mockGetImageUseCase,
            breedProfileViewDataMapper = mockBreedProfileViewDataMapper,
            savedStateHandle = mockSavedStateHandle
        )
    }

    @After
    fun tearDown() {
        confirmVerified(
            mockFindBreedUseCase,
            mockGetImageUseCase,
            mockBreedProfileViewDataMapper,
            mockSavedStateHandle
        )
    }

    @Test
    fun `Given breed and images success, When find breed, Then emit profile`() = runTest {
        // Given
        val breedId = "breedId"
        every { mockSavedStateHandle.get<String?>("breedId") } returns breedId

        val mockBreed: CatBreed = mockk()
        coEvery { mockFindBreedUseCase.invoke(breedId) } returns Result.Success(mockBreed)

        val mockImage: CatImage = mockk()
        coEvery {
            mockGetImageUseCase.invoke(breedId = breedId, page = 0, limit = 3)
        } returns Result.Success(listOf(mockImage))

        val mockViewState: BreedProfileViewState.Profile = mockk()
        coEvery {
            mockBreedProfileViewDataMapper.map(mockBreed, listOf(mockImage))
        } returns mockViewState

        viewModel.profileViewState.test {
            // When
            viewModel.findBreed()

            // Then
            assertThat(awaitItem()).isEqualTo(BreedProfileViewState.Loading)
            assertThat(awaitItem()).isEqualTo(mockViewState)
            expectNoEvents()
            verify { mockSavedStateHandle.get<String?>("breedId") }
            coVerify { mockFindBreedUseCase.invoke(breedId) }
            coVerify { mockGetImageUseCase.invoke(breedId = breedId, page = 0, limit = 3) }
            coVerify { mockBreedProfileViewDataMapper.map(mockBreed, listOf(mockImage)) }
        }
    }

    @Test
    fun `Given breed success and images failure, When find breed, Then emit profile`() = runTest {
        // Given
        val breedId = "breedId"
        every { mockSavedStateHandle.get<String?>("breedId") } returns breedId

        val mockBreed: CatBreed = mockk()
        coEvery { mockFindBreedUseCase.invoke(breedId) } returns Result.Success(mockBreed)

        coEvery {
            mockGetImageUseCase.invoke(breedId = breedId, page = 0, limit = 3)
        } returns Result.Error(mockk())

        val mockViewState: BreedProfileViewState.Profile = mockk()
        coEvery { mockBreedProfileViewDataMapper.map(mockBreed, emptyList()) } returns mockViewState

        viewModel.profileViewState.test {
            // When
            viewModel.findBreed()

            // Then
            assertThat(awaitItem()).isEqualTo(BreedProfileViewState.Loading)
            assertThat(awaitItem()).isEqualTo(mockViewState)
            expectNoEvents()
            verify { mockSavedStateHandle.get<String?>("breedId") }
            coVerify { mockFindBreedUseCase.invoke(breedId) }
            coVerify { mockGetImageUseCase.invoke(breedId = breedId, page = 0, limit = 3) }
            coVerify { mockBreedProfileViewDataMapper.map(mockBreed, emptyList()) }
        }
    }

    @Test
    fun `Given breed failure, When find breed, Then emit error`() = runTest {
        // Given
        val breedId = "breedId"
        every { mockSavedStateHandle.get<String?>("breedId") } returns breedId

        coEvery { mockFindBreedUseCase.invoke(breedId) } returns Result.Error(mockk())

        viewModel.profileViewState.test {
            // When
            viewModel.findBreed()

            // Then
            assertThat(awaitItem()).isEqualTo(BreedProfileViewState.Loading)
            assertThat(awaitItem()).isEqualTo(BreedProfileViewState.Error)
            expectNoEvents()
            verify { mockSavedStateHandle.get<String?>("breedId") }
            coVerify { mockFindBreedUseCase.invoke(breedId) }
        }
    }

}