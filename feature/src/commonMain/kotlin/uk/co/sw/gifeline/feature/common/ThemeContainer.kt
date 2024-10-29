package uk.co.sw.gifeline.feature.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.surfaceContainer(): Modifier =
    clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.surfaceContainer)
        .padding(8.dp)

@Composable
fun Modifier.primaryContainer(): Modifier =
    clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.primaryContainer)
        .padding(8.dp)

@Composable
fun Modifier.secondaryContainer(): Modifier =
    clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.secondaryContainer)
        .padding(8.dp)

@Composable
fun Modifier.tertiaryContainer(): Modifier =
    clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.tertiaryContainer)
        .padding(8.dp)
