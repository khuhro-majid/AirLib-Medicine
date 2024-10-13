package com.arlib.compose.ui.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arlib.compose.domain.usecases.ArLibGreetingsUseCase
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
    username: String, // Accept the username parameter
    viewModel: MedicineViewModel = hiltViewModel(),
) {

    // Retrieve the list of medicines from the ViewModel
    val medicines = viewModel.medicines

    Scaffold(topBar = { Toolbar(navController, "Medicine List") }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Display the greeting message with the username
            Text(
                text = "$username! ${viewModel.getGreetingMessage()}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(16.dp)
            )

            // LazyColumn to display the list of medicines
            LazyColumn {
                items(medicines) { medicine ->
                    MedicineCard(medicine) {
                        navController.navigate(Screen.MedicineDetail.createRoute(medicine.id.toString()))
                    }
                }
            }
        }
    }
}
