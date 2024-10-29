package uk.co.sw.gifeline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import uk.co.sw.gifeline.feature.navigation.GiFelineScreen
import uk.co.sw.gifeline.feature.theme.GiFelineTheme

class GiFelineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}
