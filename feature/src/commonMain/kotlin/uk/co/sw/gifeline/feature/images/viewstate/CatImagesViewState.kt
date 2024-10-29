package uk.co.sw.gifeline.feature.images.viewstate

sealed class CatImagesViewState {

    data class Images(val urls: List<String>) : CatImagesViewState()

    data object Error : CatImagesViewState()

    data object Loading : CatImagesViewState()

}