package uk.co.sw.gifeline.feature.images

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil3.compose.SubcomposeAsyncImage

@Composable
fun FullImageScreen(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            loading = { CircularProgressIndicator() },
        )
    }
}