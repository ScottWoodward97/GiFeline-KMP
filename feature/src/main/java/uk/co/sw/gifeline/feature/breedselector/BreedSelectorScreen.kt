package uk.co.sw.gifeline.feature.breedselector

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uk.co.sw.gifeline.feature.R
import uk.co.sw.gifeline.feature.breedselector.preview.BreedPreviewParameterProvider
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState.CatBreeds.CatBreedViewData
import uk.co.sw.gifeline.feature.common.ErrorScreen
import uk.co.sw.gifeline.feature.common.LoadingScreen
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun BreedSelectorScreen(
    onBreedSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BreedSelectorViewModel = hiltViewModel(),
) {
    val state: State<CatBreedViewState> = viewModel.breeds.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getCatBreeds()
    }
    BreedSelectorLayout(
        state = state.value,
        onBreedSelected = onBreedSelected,
        onRetry = viewModel::getCatBreeds,
        modifier = modifier
    )
}

@Composable
fun BreedSelectorLayout(
    state: CatBreedViewState,
    onBreedSelected: (String) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is CatBreedViewState.CatBreeds -> BreedsList(state.breeds, onBreedSelected)
            CatBreedViewState.Error -> ErrorScreen(onRetry)
            CatBreedViewState.Loading -> LoadingScreen()
        }
    }
}

@Composable
private fun BreedsList(
    breeds: List<CatBreedViewData>,
    onBreedSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        items(items = breeds, key = { it.id }) {
            Breed(breed = it, onBreedSelected = onBreedSelected)
        }
    }
}

@Composable
private fun Breed(
    breed: CatBreedViewData,
    onBreedSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onBreedSelected(breed.id) },
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 48.dp)
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
            if (breed.altNames.isNotEmpty()) {
                Text(
                    text = stringResource(
                        id = R.string.breed_selector_alt_name_fmt,
                        breed.altNames.joinToString(", ")
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BreedSelectorLayoutPreview(
    @PreviewParameter(BreedPreviewParameterProvider::class) cats: List<CatBreedViewData>
) {
    GiFelineTheme {
        BreedSelectorLayout(CatBreedViewState.CatBreeds(cats), {}, {})
    }
}