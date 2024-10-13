package com.arlib.compose.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.arlib.compose.ui.screens.login.LoginScreen
import com.arlib.compose.ui.screens.details.MedicineDetailScreen
import com.arlib.compose.ui.screens.list.MedicineListScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.arlib.compose.ui.viewmodel.MedicineViewModel

// NavGraph.kt
/**
 * Sealed class representing the different screens in the app.
 *
 * Each screen is represented as an object extending from [Screen],
 * with a unique route for navigation.
 *
 * @property route The navigation route for the screen.
 */
sealed class Screen(val route: String) {
    object Login : Screen("login") // Route for the Login screen
    object MedicineList : Screen("medicine_list/{userName}") {
        fun createRoute(userName: String) = "medicine_list/$userName"
    }// Route for the Medicine List screen
    object MedicineDetail : Screen("medicine_detail/{medicineId}") {
        /**
         * Creates a route for the Medicine Detail screen with a specific medicine ID.
         *
         * @param medicineId The ID of the medicine to be displayed.
         * @return The constructed route string.
         */
        fun createRoute(medicineId: String) = "medicine_detail/$medicineId"
    }
}

/**
 * Composable function that defines the navigation graph for the app.
 *
 * This function sets up the navigation host and its start destination,
 * along with the various composable screens.
 *
 * @param navController The NavHostController responsible for navigating between screens.
 */
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        // Composable for the Login screen
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(
            route = "medicine_list/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName")
            MedicineListScreen(navController = navController, username = userName ?: "Dear User")
        }

        // Composable for the Medicine Detail screen with argument
        composable(
            route = Screen.MedicineDetail.route,
            arguments = listOf(navArgument("medicineId") { type = NavType.StringType })
        ) { backStackEntry ->
            // Retrieve the medicineId argument from the backStackEntry
            val medicineId = backStackEntry.arguments?.getString("medicineId")
            // Uncomment the line below if using Hilt for dependency injection
            // val viewModel: MedicineViewModel = hiltViewModel()
            MedicineDetailScreen(medicineId, navController)
        }
    }
}