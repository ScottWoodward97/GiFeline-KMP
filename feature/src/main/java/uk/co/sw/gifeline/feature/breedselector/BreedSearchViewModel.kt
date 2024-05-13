package uk.co.sw.gifeline.feature.breedselector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.co.sw.gifeline.domain.breed.CatBreed
import uk.co.sw.gifeline.domain.breed.SearchCatBreedsUseCase
import uk.co.sw.gifeline.domain.response.Result
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewDataMapper
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState
import javax.inject.Inject

@HiltViewModel
class BreedSearchViewModel @Inject constructor(
    private val getCatBreedsUseCase: SearchCatBreedsUseCase,
    private val catBreedViewDataMapper: CatBreedViewDataMapper,
) : ViewModel() {

    private companion object {
        const val SEARCH_DELAY_MILLIS = 300L
    }

    private var searchJob: Job? = null

    private val _breeds = MutableStateFlow<CatBreedViewState>(CatBreedViewState.Empty)
    val breeds: StateFlow<CatBreedViewState> = _breeds

    fun searchCatBreeds(searchTerm: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DELAY_MILLIS)
            _breeds.update { CatBreedViewState.Loading }
            _breeds.update { handleResult(getCatBreedsUseCase(searchTerm)) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    private fun handleResult(result: Result<List<CatBreed>>): CatBreedViewState {
        return when (result) {
            is Result.Error -> CatBreedViewState.Error
            is Result.Success -> {
                if (result.data.isEmpty()) {
                    CatBreedViewState.Empty
                } else {
                    CatBreedViewState.CatBreeds(
                        breeds = result.data.map { catBreedViewDataMapper.map(it) }
                    )
                }
            }
        }
    }
}