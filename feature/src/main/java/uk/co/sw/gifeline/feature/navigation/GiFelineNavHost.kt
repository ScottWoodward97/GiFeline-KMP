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
            CatImagesScreen(breedId = it.arguments?.getString("breedId").orEmpty())
        }
    }
}