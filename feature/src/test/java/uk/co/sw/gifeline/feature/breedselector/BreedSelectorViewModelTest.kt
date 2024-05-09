package uk.co.sw.gifeline.feature.breedselector

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
import uk.co.sw.gifeline.domain.breed.GetCatBreedsUseCase
import uk.co.sw.gifeline.domain.response.Result
import uk.co.sw.gifeline.feature.CoroutineTestRule
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewDataMapper
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState.CatBreeds.CatBreedViewData

class BreedSelectorViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockGetCatBreedsUseCase: GetCatBreedsUseCase = mockk()
    private val mockCatBreedViewDataMapper: CatBreedViewDataMapper = mockk()

    private lateinit var viewModel: BreedSelectorViewModel

    @Before
    fun setUp() {
        viewModel = BreedSelectorViewModel(
            getCatBreedsUseCase = mockGetCatBreedsUseCase,
            catBreedViewDataMapper = mockCatBreedViewDataMapper
        )
    }

    @After
    fun tearDown() {
        confirmVerified(mockGetCatBreedsUseCase, mockCatBreedViewDataMapper)
    }

    @Test
    fun `Given breed error, When get breeds, Then emit error state`() = runTest {
        // Given
        val mockResult: Result.Error = mockk()
        coEvery { mockGetCatBreedsUseCase.invoke() } returns mockResult

        viewModel.breeds.test {
            // When
            viewModel.getCatBreeds()

            // Then
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Loading)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Error)
            expectNoEvents()

            coVerify { mockGetCatBreedsUseCase.invoke() }
        }
    }

    @Test
    fun `Given breed success, When get breeds, Then emit error state`() = runTest {
        // Given
        val mockBreed: CatBreed = mockk()
        coEvery { mockGetCatBreedsUseCase.invoke() } returns Result.Success(listOf(mockBreed))

        val mockViewData: CatBreedViewData = mockk()
        every { mockCatBreedViewDataMapper.map(mockBreed) } returns mockViewData

        viewModel.breeds.test {
            // When
            viewModel.getCatBreeds()

            // Then
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Loading)
            with(awaitItem()) {
                assertThat(this).isInstanceOf(CatBreedViewState.CatBreeds::class.java)
                assertThat((this as CatBreedViewState.CatBreeds).breeds)
                    .containsExactly(mockViewData)
            }
            expectNoEvents()

            coVerify { mockGetCatBreedsUseCase.invoke() }
            verify { mockCatBreedViewDataMapper.map(mockBreed) }
        }
    }
}