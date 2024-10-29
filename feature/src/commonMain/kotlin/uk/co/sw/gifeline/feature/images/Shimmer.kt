package uk.co.sw.gifeline.feature.images

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

@Composable
fun Modifier.shimmer(isLoading: Boolean) = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    if (isLoading) {
        val transition = rememberInfiniteTransition(label = "ShimmerInfiniteTransition")
        val animatedX by transition.animateFloat(
            initialValue = -size.width.toFloat(),
            targetValue = size.width.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
            ),
            label = "XAnimation"
        )
        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.surfaceBright,
                    MaterialTheme.colorScheme.surfaceDim,
                    MaterialTheme.colorScheme.surfaceBright,
                ),
                start = Offset(animatedX, 0f),
                end = Offset(animatedX + size.width.toFloat(), size.height.toFloat()),
            )
        ).onGloballyPositioned {
            size = it.size
        }
    } else {
        this
    }
}