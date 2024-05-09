package uk.co.sw.gifeline.feature.breedselector.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState

class BreedPreviewParameterProvider :
    PreviewParameterProvider<List<CatBreedViewState.CatBreeds.CatBreedViewData>> {
    override val values = sequenceOf(
        listOf(
            CatBreedViewState.CatBreeds.CatBreedViewData(
                id = "id1",
                name = "Cat Breed One",
                altNames = listOf("Alt Name 1", "Alt Name 2")
            ),
            CatBreedViewState.CatBreeds.CatBreedViewData(
                id = "id2",
                name = "Cat Breed Two",
                altNames = emptyList()
            ),
            CatBreedViewState.CatBreeds.CatBreedViewData(
                id = "id3",
                name = "Cat Breed Three",
                altNames = listOf(
                    "Alt Name 1",
                    "Alt Name 2",
                    "Alt Name 3",
                    "Some really long name that will require a new line"
                )
            ),
        )
    )
}