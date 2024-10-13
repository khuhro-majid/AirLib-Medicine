package com.arlib.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.arlib.compose.nav.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint
/**
 * The [MainActivity] class serves as the main entry point for the application.
 *
 * This activity is annotated with [AndroidEntryPoint], allowing it to use
 * Dagger Hilt for dependency injection. The Hilt framework will automatically
 * provide the dependencies required by this activity and its associated
 * components.
 *
 * In the [onCreate] method, the activity sets its content using Jetpack Compose,
 * initializing the navigation controller and setting up the app's navigation graph
 * with [AppNavGraph].
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppNavGraph(navController)
        }
    }
}