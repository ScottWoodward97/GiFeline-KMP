package uk.co.sw.gifeline.feature.breedprofile.profile

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewState
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BreedStats(
    stats: List<BreedProfileViewState.Profile.Stat>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        stats.forEach {
            BreedStat(
                stat = it,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                strokeColor = MaterialTheme.colorScheme.inversePrimary,
            )
        }
    }
}

@Composable
private fun BreedStat(
    stat: BreedProfileViewState.Profile.Stat,
    contentColor: Color,
    strokeColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            val animatePercentage = remember { Animatable(0f) }
            LaunchedEffect(key1 = animatePercentage) {
                animatePercentage.animateTo(
                    stat.score / stat.max.toFloat(),
                    animationSpec = tween(2000)
                )
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawArc(
                    color = contentColor,
                    startAngle = 135f,
                    sweepAngle = 270f,
                    useCenter = false,
                    style = Stroke(2.dp.toPx(), cap = StrokeCap.Round),
                    size = Size(size.width, size.height)
                )
                drawArc(
                    color = strokeColor,
                    startAngle = 135f,
                    sweepAngle = 270f * animatePercentage.value,
                    useCenter = false,
                    style = Stroke(2.dp.toPx(), cap = StrokeCap.Round),
                    size = Size(size.width, size.height)
                )
            }
            Text(
                text = stat.score.toString(),
                style = MaterialTheme.typography.labelLarge,
                color = contentColor,
                fontWeight = FontWeight.Black
            )
        }
        Text(
            text = stat.name,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun BreedStatsPreview() {
    GiFelineTheme {
        Surface(color = MaterialTheme.colorScheme.secondaryContainer) {
            BreedStats(
                stats = listOf(
                    BreedProfileViewState.Profile.Stat("Stat Name", 1, 5),
                    BreedProfileViewState.Profile.Stat("Stat Name", 3, 5),
                    BreedProfileViewState.Profile.Stat("Stat Name", 5, 5),
                )
            )
        }
    }
}