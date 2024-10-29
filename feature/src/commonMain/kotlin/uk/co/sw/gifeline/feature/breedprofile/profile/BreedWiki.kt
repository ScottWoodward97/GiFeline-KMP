package uk.co.sw.gifeline.feature.breedprofile.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gifeline.feature.generated.resources.Res
import gifeline.feature.generated.resources.breed_profile_wiki_body
import gifeline.feature.generated.resources.breed_profile_wiki_title
import gifeline.feature.generated.resources.ic_open_link
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import uk.co.sw.gifeline.feature.common.tertiaryContainer
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun BreedWiki(
    name: String,
    url: String?,
    modifier: Modifier = Modifier
) {
    var isSheetOpen by remember { mutableStateOf(false) }
    if (!url.isNullOrEmpty()) {
        Row(
            modifier = modifier
                .tertiaryContainer()
                .fillMaxWidth()
                .padding(4.dp)
                .clickable(role = Role.Button) {
                    isSheetOpen = true
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(Res.string.breed_profile_wiki_title),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,

                    )
                Text(
                    text = stringResource(Res.string.breed_profile_wiki_body, name),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
            Icon(
                painter = painterResource(Res.drawable.ic_open_link),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
            )
        }
        if (isSheetOpen) {
            WebBottomSheet(url = url, onDismiss = { isSheetOpen = false })
        }
    }
}

@Preview
@Composable
private fun BreedWikiPreview() {
    GiFelineTheme {
        BreedWiki(name = "Bengal", url = "url")
    }
}