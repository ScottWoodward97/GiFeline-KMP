package uk.co.sw.gifeline.feature.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uk.co.sw.gifeline.feature.common.ErrorScreen
import uk.co.sw.gifeline.feature.common.LoadingScreen
import uk.co.sw.gifeline.feature.images.viewstate.CatImagesViewState

@Composable
fun CatImagesScreen(
    onImageClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatImagesViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getImages()
    }
    val state = viewModel.state.collectAsState()
    CatImagesLayout(
        state = state.value,
        onImageClicked = onImageClicked,
        onRetry = viewModel::getImages,
        modifier = modifier
    )
}

@Composable
fun CatImagesLayout(
    state: CatImagesViewState,
    onImageClicked: (String) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is CatImagesViewState.Images -> CatImagesState(state.urls, onImageClicked, modifier)
        CatImagesViewState.Error -> ErrorScreen(onRetry, modifier)
        CatImagesViewState.Loading -> LoadingScreen(modifier)
    }
}

@Composable
private fun CatImagesState(
    images: List<String>,
    onImageClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(images) {
            CatImage(url = it, onImageClicked = onImageClicked)
        }
    }
}

@Composable
private fun CatImage(
    url: String,
    onImageClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        onClick = { onImageClicked(url) }
    ) {
        ShimmerImage(
            url = url,
            modifier = Modifier
                .height(200.dp)
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun ImageStatePreview() {
    CatImagesState(images = listOf("", ""), {})
}