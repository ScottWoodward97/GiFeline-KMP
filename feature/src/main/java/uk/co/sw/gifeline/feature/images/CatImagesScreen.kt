package uk.co.sw.gifeline.feature.images

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CatImagesScreen(
    breedId: String,
    modifier: Modifier = Modifier,
) {
    Text(text = breedId)
}