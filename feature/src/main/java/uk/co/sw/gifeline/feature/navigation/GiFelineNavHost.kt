package uk.co.sw.gifeline.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import uk.co.sw.gifeline.feature.breedselector.BreedSelectorScreen
import uk.co.sw.gifeline.feature.home.GiFelineHome
import uk.co.sw.gifeline.feature.images.CatImagesScreen
import uk.co.sw.gifeline.feature.images.FullImageScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun GiFelineNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier,
    ) {
        composable(route = "home") {
            GiFelineHome(onNavigateToBreedSelector = { navController.navigate("breed") })
        }
        dialog(route = "breed") {
            BreedSelectorScreen(onBreedSelected = { id -> navController.navigate("images/$id") })
        }
        composable(
            route = "images/{breedId}",
            arguments = listOf(navArgument("breedId") { type = NavType.StringType })
        ) {
            CatImagesScreen(onImageClicked = { url -> navController.navigate("image/${url.toNavSafeUrl()}") })
        }
        dialog(
            route = "image/{imageUrl}",
            arguments = listOf(navArgument("imageUrl") { type = NavType.StringType })
        ) {
            FullImageScreen(imageUrl = it.arguments?.getString("imageUrl").orEmpty())
        }
    }
}

fun String.toNavSafeUrl(): String = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())