package uk.co.sw.gifeline.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import uk.co.sw.gifeline.feature.breedprofile.BreedProfileScreen
import uk.co.sw.gifeline.feature.breedselector.BreedSearchScreen
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
            GiFelineHome(
                onNavigateToBreedSelector = { navController.navigate("allBreeds") },
                onNavigateToBreedSearch = { navController.navigate("searchBreed") },
            )
        }
        dialog(route = "allBreeds") {
            BreedSelectorScreen(onBreedSelected = { id -> navController.navigate("breed/$id") })
        }
        dialog(route = "searchBreed") {
            BreedSearchScreen(onBreedSelected = { id -> navController.navigate("breed/$id") })
        }
        composable(
            route = "breed/{breedId}",
            arguments = listOf(navArgument("breedId") { type = NavType.StringType })
        ) {
            BreedProfileScreen(
                onNavigateToImage = { url -> navController.navigate("image/${url.toNavSafeUrl()}") },
                onNavigateToGallery = { breedId -> navController.navigate("images/$breedId") }
            )
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