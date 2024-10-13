package com.arlib.compose.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arlib.compose.nav.Screen
import com.arlib.compose.ui.components.MedicineCard
import com.arlib.compose.ui.components.Toolbar
import com.arlib.compose.ui.viewmodel.MedicineViewModel

/**
 * Composable function that represents the medicine list screen of the application.
 *
 * This screen displays a list of medicines retrieved from the MedicineViewModel
 * and allows users to navigate to the details of each medicine by tapping on the corresponding card.
 *
 * @param navController The NavController used for navigation between screens.
 * @param viewModel The MedicineViewModel that provides the list of medicines.
 */
@Composable
fun MedicineListScreen(
    navController: NavController,
    viewModel: MedicineViewModel = hiltViewModel() // Injecting the MedicineViewModel using Hilt
) {
    // Retrieve the list of medicines from the ViewModel
    val medicines = viewModel.medicines

    // Scaffold provides a layout structure with a top bar
    Scaffold(topBar = { Toolbar(navController, "Medicine List") }) { innerPadding ->
        // LazyColumn to efficiently display the list of medicines inside the Scaffold's content area
        LazyColumn(
            modifier = Modifier.padding(innerPadding) // Apply the padding provided by the Scaffold
        ) {
            // Populate the LazyColumn with medicine cards
            items(medicines) { medicine -> // This version of items expects a List
                // Create a MedicineCard for each medicine item
                MedicineCard(medicine) {
                    // Navigate to the medicine detail screen when the card is clicked
                    navController.navigate(Screen.MedicineDetail.createRoute(medicine.id.toString()))
                }
            }
        }
    }
}
