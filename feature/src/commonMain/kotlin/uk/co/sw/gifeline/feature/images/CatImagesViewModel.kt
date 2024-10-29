package uk.co.sw.gifeline.feature.images

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import uk.co.sw.gifeline.domain.images.CatImage
import uk.co.sw.gifeline.domain.images.GetCatImagesUseCase
import uk.co.sw.gifeline.domain.response.Result
import uk.co.sw.gifeline.feature.images.viewstate.CatImagesViewState

@KoinViewModel
class CatImagesViewModel(
    private val getCatImagesUseCase: GetCatImagesUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private companion object {
        const val PAGE_LIMIT = 10
    }

    private val breedId: String by lazy { savedStateHandle["breedId"] ?: "" }
    private var canLoadMoreImages: Boolean = true

    private val _state: MutableStateFlow<CatImagesViewState> =
        MutableStateFlow(CatImagesViewState.Loading)
    val state: StateFlow<CatImagesViewState> = _state

    fun getImages() {
        if (canLoadMoreImages) {
            viewModelScope.launch {
                _state.update { currentState ->
                    handleResult(
                        getCatImagesUseCase(
                            breedId = breedId,
                            page = currentState.pageCount(),
                            limit = PAGE_LIMIT
                        )
                    ).checkCanLoadMore()
                        .reduce(currentState)
                }
            }
        }
    }

    private fun handleResult(result: Result<List<CatImage>>): CatImagesViewState {
        return when (result) {
            is Result.Error -> CatImagesViewState.Error
            is Result.Success -> CatImagesViewState.Images(result.data.map { it.url })
        }
    }

    private fun CatImagesViewState.pageCount(): Int {
        return when (this) {
            is CatImagesViewState.Images -> (urls.lastIndex + PAGE_LIMIT) / PAGE_LIMIT
            else -> 0
        }
    }

    private fun CatImagesViewState.checkCanLoadMore() = also {
        if (it is CatImagesViewState.Images && it.urls.size < PAGE_LIMIT) {
            canLoadMoreImages = false
        }
    }

    private fun CatImagesViewState.reduce(previousState: CatImagesViewState): CatImagesViewState {
        return if (this is CatImagesViewState.Images && previousState is CatImagesViewState.Images) {
            copy(urls = previousState.urls + urls)
        } else {
            this
        }
    }

}