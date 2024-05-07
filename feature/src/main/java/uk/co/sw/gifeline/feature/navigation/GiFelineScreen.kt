package uk.co.sw.gifeline.feature.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun GiFelineScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(modifier) {
        GiFelineNavHost(
            navController,
            Modifier.padding(it)
        )
    }

}