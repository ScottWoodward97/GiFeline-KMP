package uk.co.sw.gifeline.feature.breedprofile.preview

import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewState

class BreedProfilePreviewParameterProvider :
    PreviewParameterProvider<BreedProfileViewState.Profile> {
    override val values = sequenceOf(
        BreedProfileViewState.Profile(
            id = "id",
            imageUrls = listOf("", ""),
            name = "Cat Breed",
            altNames = listOf("Big Chonk"),
            origin = "Canada",
            description = "Oh lawd, here comes one big boi.",
            lifeSpan = "12 - 15",
            weight = "3 - 8",
            stats = listOf(
                BreedProfileViewState.Profile.Stat("Some Stat", 2, 5),
                BreedProfileViewState.Profile.Stat("Some Other Stat", 4, 5)
            ),
            wikiUrl = "url"
        ),
        BreedProfileViewState.Profile(
            id = "id",
            imageUrls = emptyList(),
            name = "Cat Breed",
            altNames = emptyList(),
            origin = "United States",
            description = "Oh lawd, here comes one big boi.",
            lifeSpan = "5 - 15",
            weight = "3 - 18",
            stats = listOf(
                BreedProfileViewState.Profile.Stat("Stat One", 1, 5),
                BreedProfileViewState.Profile.Stat("Stat Two", 3, 5),
                BreedProfileViewState.Profile.Stat("Stat Three", 5, 5),
            ),
            wikiUrl = "url"
        ),
        BreedProfileViewState.Profile(
            id = "id",
            imageUrls = emptyList(),
            name = "Cat Breed",
            altNames = emptyList(),
            origin = "United Kingdom",
            description = "Oh lawd, here comes one big boi.",
            lifeSpan = "3 - 5",
            weight = "11 - 15",
            stats = emptyList(),
            wikiUrl = "url"
        ),
    )
}