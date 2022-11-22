package com.shivamkumarjha.nasagallery.ui.main.view

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.nasaComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
    },
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))
    },
    popEnterTransition: (
    AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
    )? = {
        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
    },
    popExitTransition: (
    AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
    )? = {
        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
    },
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content
    )
}