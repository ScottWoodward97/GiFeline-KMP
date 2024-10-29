package uk.co.sw.gifeline.feature.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gifeline.feature.generated.resources.Res
import gifeline.feature.generated.resources.nav_back_content_description
import org.jetbrains.compose.resources.stringResource
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiFelineScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val scrollBehaviour =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                navigationIcon = {
                    if (currentBackStackEntry.shouldShowBackIcon()) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(Res.string.nav_back_content_description),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                },
                title = {},
                scrollBehavior = scrollBehaviour,
            )
        }
    ) {
        GiFelineNavHost(
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}

private fun NavBackStackEntry?.shouldShowBackIcon(): Boolean {
    return this?.destination?.route?.contains(Regex("breed|image")) == true
}

@Preview
@Composable
private fun ScreenPreview() {
    GiFelineTheme {
        GiFelineScreen()
    }
}