package uk.co.sw.gifeline.feature.breedprofile.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import gifeline.feature.generated.resources.Res
import gifeline.feature.generated.resources.breed_profile_info_gallery_title
import gifeline.feature.generated.resources.breed_profile_info_lifespan_measure
import gifeline.feature.generated.resources.breed_profile_info_lifespan_title
import gifeline.feature.generated.resources.breed_profile_info_origin_title
import gifeline.feature.generated.resources.breed_profile_info_weight_measure
import gifeline.feature.generated.resources.breed_profile_info_weight_title
import gifeline.feature.generated.resources.ic_photo_gallery
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uk.co.sw.gifeline.feature.common.secondaryContainer
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BreedInfo(
    origin: String,
    weight: String,
    lifeSpan: String,
    onGalleryClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Column(
            modifier = Modifier
                .secondaryContainer()
                .width(IntrinsicSize.Max)
                .fillMaxHeight()
        ) {
            Text(
                text = stringResource(Res.string.breed_profile_info_lifespan_title),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),

                )
            Text(
                text = lifeSpan,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(Res.string.breed_profile_info_lifespan_measure),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Column(
            modifier = Modifier
                .secondaryContainer()
                .width(IntrinsicSize.Max)
                .fillMaxHeight()
        ) {
            Text(
                text = stringResource(Res.string.breed_profile_info_weight_title),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),

                )
            Text(
                text = weight,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(Res.string.breed_profile_info_weight_measure),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Column(
            modifier = Modifier
                .secondaryContainer()
                .width(IntrinsicSize.Max)
                .fillMaxHeight()
        ) {
            Text(
                text = stringResource(Res.string.breed_profile_info_origin_title),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = origin,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .secondaryContainer()
                .width(IntrinsicSize.Max)
                .fillMaxHeight()
                .clickable(role = Role.Button) { onGalleryClicked() },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.breed_profile_info_gallery_title),
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.fillMaxWidth(),
            )
            Icon(
                painter = painterResource(Res.drawable.ic_photo_gallery),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun BreedInfoPreview() {
    GiFelineTheme {
        BreedInfo(origin = "Country", weight = "13-15", lifeSpan = "10-11", onGalleryClicked = { })
    }
}