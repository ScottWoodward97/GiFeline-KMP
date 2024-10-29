package uk.co.sw.gifeline.feature.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.co.sw.gifeline.feature.R
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun SpinningCat(size: Dp) {
    val transition = rememberInfiniteTransition(label = "CatInfinite")
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 2000,
                easing = LinearEasing,
            )
        ),
        label = "CatInfinite.Rotation"
    )
    val animatedScale by transition.animateFloat(
        initialValue = 0.75f,
        targetValue = 1.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "CatInfinite.Scale"
    )
    Box(Modifier.size(size * 1.25f), contentAlignment = Alignment.Center) {
        Icon(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = null,
            modifier = Modifier
                .rotate(rotation)
                .graphicsLayer {
                    scaleX = animatedScale
                    scaleY = animatedScale
                    transformOrigin = TransformOrigin.Center
                },
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
private fun SpinningCatPreview() {
    GiFelineTheme {
        SpinningCat(100.dp)
    }
}