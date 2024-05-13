package uk.co.sw.gifeline.feature.breedselector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uk.co.sw.gifeline.feature.breedselector.preview.BreedPreviewParameterProvider
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun BreedSearchScreen(
    onBreedSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BreedSearchViewModel = hiltViewModel(),
) {
    val state = viewModel.breeds.collectAsState()
    BreedSearchLayout(
        state = state.value,
        onSearch = viewModel::searchCatBreeds,
        onBreedSelected = onBreedSelected,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface),
    )
}

@Composable
private fun BreedSearchLayout(
    state: CatBreedViewState,
    onSearch: (String) -> Unit,
    onBreedSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchTerm by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier) {
        TextField(
            value = searchTerm,
            onValueChange = {
                searchTerm = it
                onSearch(it)
            },
            modifier = Modifier.fillMaxWidth()
        )
        BreedSelectorLayout(
            state = state,
            onBreedSelected = onBreedSelected,
            onRetry = { onSearch(searchTerm) },
        )
    }
}

@Preview
@Composable
private fun BreedSearchLayoutPreview(
    @PreviewParameter(BreedPreviewParameterProvider::class) cats: List<CatBreedViewState.CatBreeds.CatBreedViewData>
) {
    GiFelineTheme {
        BreedSearchLayout(
            state = CatBreedViewState.CatBreeds(cats),
            onSearch = {},
            onBreedSelected = {},
        )
    }
}