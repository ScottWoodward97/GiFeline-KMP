package uk.co.sw.gifeline.feature.breedselector

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.co.sw.gifeline.domain.breed.CatBreed
import uk.co.sw.gifeline.domain.breed.SearchCatBreedsUseCase
import uk.co.sw.gifeline.domain.response.Result
import uk.co.sw.gifeline.feature.CoroutineTestRule
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewDataMapper
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState

@OptIn(ExperimentalCoroutinesApi::class)
class BreedSearchViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockSearchCatBreedsUseCase: SearchCatBreedsUseCase = mockk()
    private val mockCatBreedViewDataMapper: CatBreedViewDataMapper = mockk()

    private lateinit var viewModel: BreedSearchViewModel

    @Before
    fun setUp() {
        viewModel = BreedSearchViewModel(
            getCatBreedsUseCase = mockSearchCatBreedsUseCase,
            catBreedViewDataMapper = mockCatBreedViewDataMapper
        )
    }

    @After
    fun tearDown() {
        confirmVerified(mockSearchCatBreedsUseCase, mockCatBreedViewDataMapper)
    }

    @Test
    fun `Given breed error, When get breeds, Then emit error state`() = runTest {
        // Given
        val mockResult: Result.Error = mockk()
        coEvery { mockSearchCatBreedsUseCase.invoke("term") } returns mockResult

        viewModel.breeds.test {
            // When
            viewModel.searchCatBreeds("term")

            // Then
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Empty)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Loading)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Error)
            expectNoEvents()

            coVerify { mockSearchCatBreedsUseCase.invoke("term") }
        }
    }

    @Test
    fun `Given breed success, When get breeds, Then emit breeds state`() = runTest {
        // Given
        val mockBreed: CatBreed = mockk()
        coEvery { mockSearchCatBreedsUseCase.invoke("term") } returns
                Result.Success(listOf(mockBreed))

        val mockViewData: CatBreedViewState.CatBreeds.CatBreedViewData = mockk()
        every { mockCatBreedViewDataMapper.map(mockBreed) } returns mockViewData

        viewModel.breeds.test {
            // When
            viewModel.searchCatBreeds("term")

            // Then
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Empty)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Loading)
            with(awaitItem()) {
                assertThat(this).isInstanceOf(CatBreedViewState.CatBreeds::class.java)
                assertThat((this as CatBreedViewState.CatBreeds).breeds)
                    .containsExactly(mockViewData)
            }
            expectNoEvents()

            coVerify { mockSearchCatBreedsUseCase.invoke("term") }
            verify { mockCatBreedViewDataMapper.map(mockBreed) }
        }
    }

    @Test
    fun `Given breed success but no breeds, When search breeds, Then emit empty state`() = runTest {
        // Given
        coEvery { mockSearchCatBreedsUseCase.invoke("term") } returns
                Result.Success(emptyList())

        viewModel.breeds.test {
            // When
            viewModel.searchCatBreeds("term")

            // Then
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Empty)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Loading)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Empty)
            expectNoEvents()

            coVerify { mockSearchCatBreedsUseCase.invoke("term") }
        }
    }

    @Test
    fun `Given search twice within delay, When search second time, Then search once`() = runTest {
        // Given
        coEvery { mockSearchCatBreedsUseCase.invoke("term") } returns
                Result.Success(emptyList())

        viewModel.breeds.test {
            // When
            viewModel.searchCatBreeds("term")
            advanceTimeBy(250)
            viewModel.searchCatBreeds("term")

            // Then
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Empty)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Loading)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Empty)
            expectNoEvents()

            coVerify { mockSearchCatBreedsUseCase.invoke("term") }
        }
    }

    @Test
    fun `Given search twice outside delay, When search second time, Then search twice`() = runTest {
        // Given
        coEvery { mockSearchCatBreedsUseCase.invoke("term") } returns
                Result.Success(emptyList())

        viewModel.breeds.test {
            // When
            viewModel.searchCatBreeds("term")
            advanceTimeBy(350)
            viewModel.searchCatBreeds("term")

            // Then
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Empty)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Loading)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Empty)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Loading)
            assertThat(awaitItem()).isEqualTo(CatBreedViewState.Empty)
            expectNoEvents()

            coVerify(exactly = 2) { mockSearchCatBreedsUseCase.invoke("term") }
        }
    }

}