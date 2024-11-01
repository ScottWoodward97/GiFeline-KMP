package uk.co.sw.gifeline.feature.breedprofile.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun WebDisplay(
    url: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
)