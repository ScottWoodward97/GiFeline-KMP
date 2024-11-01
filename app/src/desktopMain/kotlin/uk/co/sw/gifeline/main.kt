package uk.co.sw.gifeline

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import uk.co.sw.gifeline.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "GiFeline",
        ) {
            App()
        }
    }
}