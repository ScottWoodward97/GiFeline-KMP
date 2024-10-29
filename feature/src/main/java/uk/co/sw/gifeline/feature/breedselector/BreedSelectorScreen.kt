package uk.co.sw.gifeline.feature.breedselector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import uk.co.sw.gifeline.feature.breedselector.viewstate.CatBreedViewState

@Composable
fun BreedSelectorScreen(
    onBreedSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BreedSelectorViewModel = koinViewModel(),
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
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
    )
}