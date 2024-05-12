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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.co.sw.gifeline.feature.R
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun GiFelineHome(
    onNavigateToBreedSelector: () -> Unit,
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
        ) {
            Text(
                text = stringResource(R.string.home_title),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(R.string.home_sub_title),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.home_select_breed_body),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigateToBreedSelector,
            ) {
                Text(text = stringResource(R.string.home_select_breed_action))
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.home_small_print),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(R.string.home_copyright),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
private fun GiFelineHomePreview() {
    GiFelineTheme {
        GiFelineHome({})
    }
}