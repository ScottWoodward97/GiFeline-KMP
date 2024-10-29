package uk.co.sw.gifeline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class GiFelineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}
