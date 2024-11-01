package uk.co.sw.gifeline.feature.breedprofile.profile

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import uk.co.sw.gifeline.feature.images.ShimmerImage
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun BreedImageGallery(
    imageUrls: List<String>,
    onImageClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pagerState = rememberPagerState { imageUrls.size }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShimmerImage(
                url = imageUrls[it],
                modifier = Modifier.clickable(role = Role.Button) { onImageClicked(imageUrls[it]) }
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
            repeat(pagerState.pageCount) {
                val animatedSize = animateDpAsState(
                    targetValue = if (pagerState.currentPage == it) 8.dp else 4.dp,
                    label = "GalleryPagerState"
                )
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onSurface)
                        .size(animatedSize.value)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ImageGalleryPreview() {
    GiFelineTheme {
        Surface {
            BreedImageGallery(imageUrls = listOf("url", "url", "url"), onImageClicked = {})
        }
    }
}