package uk.co.sw.gifeline.feature.images

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import uk.co.sw.gifeline.feature.common.ErrorScreen
import uk.co.sw.gifeline.feature.common.LoadingScreen
import uk.co.sw.gifeline.feature.images.viewstate.CatImagesViewState
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun CatImagesScreen(
    onImageClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatImagesViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getImages()
    }
    val state = viewModel.state.collectAsState()
    CatImagesLayout(
        state = state.value,
        onImageClicked = onImageClicked,
        onLoadMore = viewModel::getImages,
        onRetry = viewModel::getImages,
        modifier = modifier
    )
}

@Composable
fun CatImagesLayout(
    state: CatImagesViewState,
    onImageClicked: (String) -> Unit,
    onLoadMore: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is CatImagesViewState.Images -> CatImagesState(state.urls, onImageClicked, onLoadMore, modifier)
        CatImagesViewState.Error -> ErrorScreen(onRetry, modifier)
        CatImagesViewState.Loading -> LoadingScreen(modifier)
    }
}

@Composable
private fun CatImagesState(
    images: List<String>,
    onImageClicked: (String) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(images) { index, imageUrl ->
            ShimmerImage(
                url = imageUrl,
                modifier = Modifier
                    .wrapContentHeight()
                    .clickable(role = Role.Button) { onImageClicked(imageUrl) },
            )
            if (index == images.lastIndex) {
                onLoadMore()
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ImageStatePreview() {
    GiFelineTheme {
        CatImagesState(images = listOf("", ""), {}, {})
    }
}