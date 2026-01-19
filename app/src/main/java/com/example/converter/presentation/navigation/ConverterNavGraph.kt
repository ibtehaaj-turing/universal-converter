package com.example.converter.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.converter.domain.model.ConverterCategory
import com.example.converter.presentation.converter.ConverterScreen
import com.example.converter.presentation.home.HomeScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Converter : Screen("converter/{category}") {
        fun createRoute(category: ConverterCategory): String {
            return "converter/${category.name}"
        }
    }
}

private const val TRANSITION_DURATION = 300

@Composable
fun ConverterNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(TRANSITION_DURATION)
                ) + fadeIn(tween(TRANSITION_DURATION))
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(TRANSITION_DURATION)
                ) + fadeOut(tween(TRANSITION_DURATION))
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(TRANSITION_DURATION)
                ) + fadeIn(tween(TRANSITION_DURATION))
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(TRANSITION_DURATION)
                ) + fadeOut(tween(TRANSITION_DURATION))
            }
        ) {
            HomeScreen(
                onCategoryClick = { category ->
                    navController.navigate(Screen.Converter.createRoute(category))
                }
            )
        }

        composable(
            route = Screen.Converter.route,
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(TRANSITION_DURATION)
                ) + fadeIn(tween(TRANSITION_DURATION))
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(TRANSITION_DURATION)
                ) + fadeOut(tween(TRANSITION_DURATION))
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(TRANSITION_DURATION)
                ) + fadeIn(tween(TRANSITION_DURATION))
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(TRANSITION_DURATION)
                ) + fadeOut(tween(TRANSITION_DURATION))
            }
        ) {
            ConverterScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
