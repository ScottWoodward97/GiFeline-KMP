package uk.co.sw.gifeline.feature.breedselector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.co.sw.gifeline.domain.breed.CatBreed
import uk.co.sw.gifeline.domain.breed.GetCatBreedsUseCase
import uk.co.sw.gifeline.domain.response.Result
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewDataMapper
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState
import javax.inject.Inject

@HiltViewModel
class BreedSelectorViewModel @Inject constructor(
    private val getCatBreedsUseCase: GetCatBreedsUseCase,
    private val catBreedViewDataMapper: CatBreedViewDataMapper,
) : ViewModel() {

    private val _breeds = MutableStateFlow<CatBreedViewState>(CatBreedViewState.Loading)
    val breeds: StateFlow<CatBreedViewState> = _breeds

    fun getCatBreeds() {
        viewModelScope.launch {
            _breeds.update { handleResult(getCatBreedsUseCase()) }
        }
    }

    private fun handleResult(result: Result<List<CatBreed>>): CatBreedViewState {
        return when (result) {
            is Result.Error -> CatBreedViewState.Error
            is Result.Success -> CatBreedViewState.CatBreeds(
                breeds = result.data.map { catBreedViewDataMapper.map(it) }
            )
        }
    }

}