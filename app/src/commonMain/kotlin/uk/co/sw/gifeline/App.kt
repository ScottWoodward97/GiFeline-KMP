package uk.co.sw.gifeline

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import uk.co.sw.gifeline.feature.navigation.GiFelineScreen
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

@Composable
fun App() {
    GiFelineTheme {
        GiFelineScreen()
    }
}

@Preview
@Composable
private fun AppPreview() {
    App()
}