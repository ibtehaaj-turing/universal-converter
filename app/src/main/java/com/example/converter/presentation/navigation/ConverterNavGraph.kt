package com.example.converter.presentation.navigation

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

@Composable
fun ConverterNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
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
            )
        ) {
            ConverterScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
