package com.shivamkumarjha.nasagallery.ui.main.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.shivamkumarjha.nasagallery.ui.main.model.MainEvent
import com.shivamkumarjha.nasagallery.ui.main.viewmodel.MainViewModel

private const val NAV_MAIN = "MAIN"
private const val NAV_DETAIL = "DETAIL"
private const val ARG_INDEX = "INDEX"

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavHost(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = NAV_MAIN
) {

    fun handleMainEvents(mainEvent: MainEvent) {
        when (mainEvent) {
            is MainEvent.OpenDetail -> navController.navigate("$NAV_DETAIL/${mainEvent.index}")
        }
    }

    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        nasaComposable(
            NAV_MAIN
        ) {
            MainScreen(viewModel) {
                handleMainEvents(it)
            }
        }

        nasaComposable(
            "$NAV_DETAIL/{$ARG_INDEX}",
            arguments = listOf(navArgument(ARG_INDEX) { type = NavType.IntType }),
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(ARG_INDEX)?.let { index ->
                DetailScreen(viewModel, index) {
                    navController.navigateUp()
                }
            }
        }
    }
}