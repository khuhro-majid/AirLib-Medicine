package com.arlib.compose.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.arlib.compose.R
import com.arlib.compose.ui.theme.toolBarColor


/**
 * Composable function that creates a toolbar for navigation and displaying the screen title.
 *
 * The toolbar includes a back navigation button and displays the specified screen title.
 *
 * @param navController The [NavController] used for navigating between screens in the app.
 * @param screenTitle The title to display in the toolbar.
 */
@Composable
fun Toolbar(navController: NavController, screenTitle: String) {
    TopAppBar(
        title = { Text(text = screenTitle) }, // Set the title of the toolbar
        backgroundColor = toolBarColor, // Set the toolbar background color
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { // Navigate back when the icon is clicked
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back), // Replace with your back icon resource
                    contentDescription = "Back" // Describe the icon for accessibility
                )
            }
        }
    )
}
