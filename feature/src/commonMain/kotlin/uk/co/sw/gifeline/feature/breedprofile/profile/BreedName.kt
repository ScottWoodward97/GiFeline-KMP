package uk.co.sw.gifeline.feature.breedprofile.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import gifeline.feature.generated.resources.Res
import gifeline.feature.generated.resources.breed_alt_name_fmt
import org.jetbrains.compose.resources.stringResource
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun BreedName(
    name: String,
    altNames: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        if (altNames.isNotEmpty()) {
            Text(
                text = stringResource(
                    Res.string.breed_alt_name_fmt,
                    altNames.joinToString(", ")
                ),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview
@Composable
private fun BreedNamePreview() {
    GiFelineTheme {
        Surface {
            BreedName(name = "Cat Name", altNames = listOf("A different cat name"))
        }
    }
}