package uk.co.sw.gifeline.feature.breedselector.viewstate

sealed class CatBreedViewState {

    data class CatBreeds(val breeds: List<CatBreedViewData>) : CatBreedViewState() {
        data class CatBreedViewData(val id: String, val name: String, val altNames: List<String>)
    }

    data object Error : CatBreedViewState()

    data object Loading : CatBreedViewState()

}