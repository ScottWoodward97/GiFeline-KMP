package uk.co.sw.gifeline.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import gifeline.feature.generated.resources.Res
import gifeline.feature.generated.resources.home_copyright
import gifeline.feature.generated.resources.home_search_breeds_action
import gifeline.feature.generated.resources.home_search_breeds_body
import gifeline.feature.generated.resources.home_select_breed_action
import gifeline.feature.generated.resources.home_select_breed_body
import gifeline.feature.generated.resources.home_small_print
import gifeline.feature.generated.resources.home_sub_title
import gifeline.feature.generated.resources.home_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import uk.co.sw.gifeline.feature.common.surfaceContainer
import uk.co.sw.gifeline.feature.common.tertiaryContainer
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun GiFelineHome(
    onNavigateToBreedSelector: () -> Unit,
    onNavigateToBreedSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(Res.string.home_title),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = stringResource(Res.string.home_sub_title),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .surfaceContainer()
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .surfaceContainer()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(Res.string.home_search_breeds_body),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .tertiaryContainer()
                    .fillMaxWidth()
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToBreedSearch,
            ) {
                Text(
                    text = stringResource(Res.string.home_search_breeds_action),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .surfaceContainer()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(Res.string.home_select_breed_body),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .tertiaryContainer()
                    .fillMaxWidth()
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToBreedSelector,
            ) {
                Text(
                    text = stringResource(Res.string.home_select_breed_action),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Column(
            modifier = Modifier.surfaceContainer(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.home_small_print),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = stringResource(Res.string.home_copyright),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Preview
@Composable
private fun GiFelineHomePreview() {
    GiFelineTheme {
        GiFelineHome({}, {})
    }
}