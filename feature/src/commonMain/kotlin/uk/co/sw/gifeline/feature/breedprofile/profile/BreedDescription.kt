package uk.co.sw.gifeline.feature.breedprofile.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import gifeline.feature.generated.resources.Res
import gifeline.feature.generated.resources.breed_profile_description_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun BreedDescription(
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(Res.string.breed_profile_description_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
private fun BreedDescriptionPreview() {
    GiFelineTheme {
        Surface {
            BreedDescription(description = "The Siberian is a majestic cat breed with a thick, " +
                    "plush coat that comes in a variety of colors. Known for their intelligence " +
                    "and playful nature, they are often referred to as \"dog-like cats\" due to " +
                    "their loyalty and eagerness to please.")
        }
    }
}