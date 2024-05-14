package uk.co.sw.gifeline.feature.images

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.co.sw.gifeline.domain.images.CatImage
import uk.co.sw.gifeline.domain.images.GetCatImagesUseCase
import uk.co.sw.gifeline.domain.response.Result
import uk.co.sw.gifeline.feature.CoroutineTestRule
import uk.co.sw.gifeline.feature.images.viewstate.CatImagesViewState

@OptIn(ExperimentalCoroutinesApi::class)
class CatImagesViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockGetCatImagesUseCase: GetCatImagesUseCase = mockk()
    private val mockSavedStateHandle: SavedStateHandle = mockk()

    private lateinit var viewModel: CatImagesViewModel

    @Before
    fun setUp() {
        viewModel = CatImagesViewModel(
            getCatImagesUseCase = mockGetCatImagesUseCase,
            savedStateHandle = mockSavedStateHandle
        )
    }

    @After
    fun tearDown() {
        confirmVerified(mockGetCatImagesUseCase, mockSavedStateHandle)
    }

    @Test
    fun `Given images error, When get images, Then emit error`() = runTest {
        // Given
        val breedId = "breedId"
        every { mockSavedStateHandle.get<String?>("breedId") } returns breedId

        coEvery {
            mockGetCatImagesUseCase.invoke(breedId, any(), any())
        } returns Result.Error(mockk())

        viewModel.state.test {
            // When
            viewModel.getImages()

            // Then
            assertThat(awaitItem()).isEqualTo(CatImagesViewState.Loading)
            assertThat(awaitItem()).isEqualTo(CatImagesViewState.Error)
            expectNoEvents()
            verify { mockSavedStateHandle.get<String?>("breedId") }
            coVerify { mockGetCatImagesUseCase.invoke(breedId) }
        }
    }

    @Test
    fun `Given images success, When get images, Then emit images`() = runTest {
        // Given
        val breedId = "breedId"
        every { mockSavedStateHandle.get<String?>("breedId") } returns breedId

        val mockImage: CatImage = mockk {
            every { url } returns "url"
        }
        coEvery {
            mockGetCatImagesUseCase.invoke(breedId = breedId, page = 0, limit = 10)
        } returns Result.Success(listOf(mockImage))

        viewModel.state.test {
            // When
            viewModel.getImages()

            // Then
            assertThat(awaitItem()).isEqualTo(CatImagesViewState.Loading)
            with(awaitItem()) {
                assertThat(this).isInstanceOf(CatImagesViewState.Images::class.java)
                assertThat((this as CatImagesViewState.Images).urls).containsExactly("url")
            }
            expectNoEvents()
            verify { mockSavedStateHandle.get<String?>("breedId") }
            coVerify { mockGetCatImagesUseCase.invoke(breedId = breedId, page = 0, limit = 10) }
        }
    }

    @Test
    fun `Given full page, When get images, Then load second page`() = runTest {
        // Given
        val breedId = "breedId"
        every { mockSavedStateHandle.get<String?>("breedId") } returns breedId

        val mockImage: CatImage = mockk {
            every { url } returns "url"
        }
        val imageList = buildList { repeat(10) { add(mockImage) } }
        coEvery {
            mockGetCatImagesUseCase.invoke(breedId = breedId, page = any(), limit = 10)
        } returns Result.Success(imageList)

        viewModel.state.test {
            // When
            viewModel.getImages()
            viewModel.getImages()

            // Then
            assertThat(awaitItem()).isEqualTo(CatImagesViewState.Loading)
            with(awaitItem()) {
                assertThat(this).isInstanceOf(CatImagesViewState.Images::class.java)
                assertThat((this as CatImagesViewState.Images).urls).hasSize(10)
            }
            with(awaitItem()) {
                assertThat(this).isInstanceOf(CatImagesViewState.Images::class.java)
                assertThat((this as CatImagesViewState.Images).urls).hasSize(20)
            }
            expectNoEvents()
            verify { mockSavedStateHandle.get<String?>("breedId") }
            coVerify { mockGetCatImagesUseCase.invoke(breedId = breedId, page = 0, limit = 10) }
            coVerify { mockGetCatImagesUseCase.invoke(breedId = breedId, page = 1, limit = 10) }
        }
    }

    @Test
    fun `Given not full page, When get images, Then don't load second page`() = runTest {
        // Given
        val breedId = "breedId"
        every { mockSavedStateHandle.get<String?>("breedId") } returns breedId

        val mockImage: CatImage = mockk {
            every { url } returns "url"
        }
        val imageList = buildList { repeat(9) { add(mockImage) } }
        coEvery {
            mockGetCatImagesUseCase.invoke(breedId = breedId, page = any(), limit = 10)
        } returns Result.Success(imageList)

        viewModel.state.test {
            // When
            viewModel.getImages()
            advanceUntilIdle()
            viewModel.getImages()

            // Then
            assertThat(awaitItem()).isEqualTo(CatImagesViewState.Loading)
            with(awaitItem()) {
                assertThat(this).isInstanceOf(CatImagesViewState.Images::class.java)
                assertThat((this as CatImagesViewState.Images).urls).hasSize(9)
            }
            expectNoEvents()
            verify { mockSavedStateHandle.get<String?>("breedId") }
            coVerify { mockGetCatImagesUseCase.invoke(breedId = breedId, page = 0, limit = 10) }
        }
    }
}