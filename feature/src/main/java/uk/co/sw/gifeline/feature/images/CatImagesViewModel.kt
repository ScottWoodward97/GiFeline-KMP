package uk.co.sw.gifeline.feature.images

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.co.sw.gifeline.domain.images.CatImage
import uk.co.sw.gifeline.domain.images.GetCatImagesUseCase
import uk.co.sw.gifeline.domain.response.Result
import uk.co.sw.gifeline.feature.images.viewstate.CatImagesViewState
import javax.inject.Inject

@HiltViewModel
class CatImagesViewModel @Inject constructor(
    private val getCatImagesUseCase: GetCatImagesUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val breedId: String by lazy { savedStateHandle["breedId"] ?: "" }

    private val _state: MutableStateFlow<CatImagesViewState> = MutableStateFlow(CatImagesViewState.Loading)
    val state: StateFlow<CatImagesViewState> = _state

    fun getImages() {
        viewModelScope.launch {
            _state.update { handleResult(getCatImagesUseCase(breedId)) }
        }
    }

    private fun handleResult(result: Result<List<CatImage>>): CatImagesViewState {
        return when (result){
            is Result.Error -> CatImagesViewState.Error
            is Result.Success -> CatImagesViewState.Images(result.data.map { it.url })
        }
    }

}