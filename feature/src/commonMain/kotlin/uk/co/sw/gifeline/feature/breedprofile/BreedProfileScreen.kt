package uk.co.sw.gifeline.feature.breedprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.koin.compose.viewmodel.koinViewModel
import uk.co.sw.gifeline.feature.breedprofile.preview.BreedProfilePreviewParameterProvider
import uk.co.sw.gifeline.feature.breedprofile.profile.BreedDescription
import uk.co.sw.gifeline.feature.breedprofile.profile.BreedImageGallery
import uk.co.sw.gifeline.feature.breedprofile.profile.BreedInfo
import uk.co.sw.gifeline.feature.breedprofile.profile.BreedName
import uk.co.sw.gifeline.feature.breedprofile.profile.BreedStats
import uk.co.sw.gifeline.feature.breedprofile.profile.BreedWiki
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewState
import uk.co.sw.gifeline.feature.common.ErrorScreen
import uk.co.sw.gifeline.feature.common.LoadingScreen
import uk.co.sw.gifeline.feature.common.surfaceContainer
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun BreedProfileScreen(
    modifier: Modifier = Modifier,
    onNavigateToImage: (String) -> Unit,
    onNavigateToGallery: (String) -> Unit,
    viewModel: BreedProfileViewModel = koinViewModel(),
) {
    val state = viewModel.profileViewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.findBreed()
    }
    BreedProfileLayout(
        state = state.value,
        onImageClicked = onNavigateToImage,
        onGalleryClicked = onNavigateToGallery,
        onRetry = viewModel::findBreed,
        modifier = modifier
    )
}

@Composable
private fun BreedProfileLayout(
    state: BreedProfileViewState,
    onImageClicked: (String) -> Unit,
    onGalleryClicked: (String) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is BreedProfileViewState.Profile -> BreedProfile(
            profile = state,
            onImageClicked = onImageClicked,
            onGalleryClicked = onGalleryClicked,
            modifier = modifier
        )

        BreedProfileViewState.Error -> ErrorScreen(onRetry = onRetry)

        BreedProfileViewState.Loading -> LoadingScreen()
    }
}

@Composable
private fun BreedProfile(
    profile: BreedProfileViewState.Profile,
    onImageClicked: (String) -> Unit,
    onGalleryClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            BreedImageGallery(
                imageUrls = profile.imageUrls,
                onImageClicked = onImageClicked,
                modifier = Modifier.fillParentMaxHeight(0.4f)
            )
        }
        item {
            BreedPrimaryInfo(
                profile = profile,
                onGalleryClicked = onGalleryClicked
            )
        }
        item {
            BreedSecondaryInfo(
                description = profile.description,
                stats = profile.stats
            )
        }
        item {
            BreedWiki(
                name = profile.name,
                url = profile.wikiUrl
            )
        }
    }
}

@Composable
private fun BreedPrimaryInfo(
    profile: BreedProfileViewState.Profile,
    onGalleryClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        BreedName(name = profile.name, altNames = profile.altNames)
        BreedInfo(
            origin = profile.origin,
            lifeSpan = profile.lifeSpan,
            weight = profile.weight,
            onGalleryClicked = { onGalleryClicked(profile.id) })
    }
}

@Composable
private fun BreedSecondaryInfo(
    description: String,
    stats: List<BreedProfileViewState.Profile.Stat>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.surfaceContainer(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        BreedDescription(description = description)
        BreedStats(stats = stats)
    }
}

@Preview
@Composable
private fun BreedProfilePreview(
    @PreviewParameter(BreedProfilePreviewParameterProvider::class) profile: BreedProfileViewState.Profile
) {
    GiFelineTheme {
        BreedProfile(profile, {}, {})
    }
}