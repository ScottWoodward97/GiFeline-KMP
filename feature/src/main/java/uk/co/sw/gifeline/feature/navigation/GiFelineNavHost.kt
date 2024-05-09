package uk.co.sw.gifeline.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import uk.co.sw.gifeline.feature.breedselector.BreedSelectorScreen
import uk.co.sw.gifeline.feature.home.GiFelineHome

@Composable
fun GiFelineNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier,
    ){
        composable("home"){ GiFelineHome(onNavigateToBreedSelector = { navController.navigate("breed") }) }
        dialog("breed"){ BreedSelectorScreen({}) }
    }
}