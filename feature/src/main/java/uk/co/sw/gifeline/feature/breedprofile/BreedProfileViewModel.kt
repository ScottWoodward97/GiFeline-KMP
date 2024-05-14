package uk.co.sw.gifeline.feature.breedprofile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.co.sw.gifeline.domain.breed.CatBreed
import uk.co.sw.gifeline.domain.breed.FindBreedUseCase
import uk.co.sw.gifeline.domain.images.CatImage
import uk.co.sw.gifeline.domain.images.GetCatImagesUseCase
import uk.co.sw.gifeline.domain.response.Result
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewDataMapper
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewState
import javax.inject.Inject

@HiltViewModel
class BreedProfileViewModel @Inject constructor(
    private val findBreedUseCase: FindBreedUseCase,
    private val getImageUseCase: GetCatImagesUseCase,
    private val breedProfileViewDataMapper: BreedProfileViewDataMapper,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val breedId: String by lazy { savedStateHandle["breedId"] ?: "" }

    private val _profileViewState: MutableStateFlow<BreedProfileViewState> =
        MutableStateFlow(BreedProfileViewState.Loading)
    val profileViewState: StateFlow<BreedProfileViewState> = _profileViewState

    fun findBreed() {
        viewModelScope.launch {
            _profileViewState.update { BreedProfileViewState.Loading }
            _profileViewState.update { handleResult(findBreedUseCase(breedId)) }
        }
    }

    private suspend fun handleResult(result: Result<CatBreed>): BreedProfileViewState {
        return when (result) {
            is Result.Success -> {
                val catImages: List<CatImage> =
                    when (val imageResult = getImageUseCase(breedId, 3)) {
                        is Result.Success -> imageResult.data
                        is Result.Error -> emptyList()
                    }
                breedProfileViewDataMapper.map(result.data, catImages)
            }

            is Result.Error -> BreedProfileViewState.Error
        }
    }

}