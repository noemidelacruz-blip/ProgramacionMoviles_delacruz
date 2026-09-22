package com.delacruz.navlab.navigation

package com.delacruz.navlab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.delacruz.navlab.screens.DetailScreen
import com.delacruz.navlab.screens.HomeScreen
import com.delacruz.navlab.screens.ListScreen
import com.delacruz.navlab.screens.ProfileScreen

/**
 * Componente principal de navegación que define el grafo y las rutas de la aplicación.
 */
@Composable
fun AppNavigation() {
    // crea y mantiene el controlador de navegación
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Screen.Home.route -> "home" (inicio / startDestination)
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        // Screen.List.route -> "list" (ruta simple)
        composable(route = Screen.List.route) {
            ListScreen(navController = navController)
        }

        // Screen.Profile.route -> "profile" (ruta simple)
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        // Screen.Detail.route -> "detail/{itemId}" (con argumento)
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
            DetailScreen(navController = navController, itemId = itemId)
        }
    }
}