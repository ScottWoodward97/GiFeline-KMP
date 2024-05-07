package uk.co.sw.gifeline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import uk.co.sw.gifeline.feature.navigation.GiFelineNavHost
import uk.co.sw.gifeline.feature.navigation.GiFelineScreen
import uk.co.sw.gifeline.ui.theme.GiFelineTheme

@AndroidEntryPoint
class GiFelineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiFelineTheme {
                GiFelineScreen()
            }
        }
    }
}
