package com.arlib.compose.ui.screens.details

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arlib.compose.R
import com.arlib.compose.data.local.entities.Medicine
import com.arlib.compose.ui.components.Toolbar
import com.arlib.compose.ui.viewmodel.MedicineViewModel

/**
 * Composable function that represents the medicine detail screen.
 *
 * This screen displays detailed information about a specific medicine, including its name, dose,
 * strength, and description. It also features an animated image of the medicine.
 *
 * @param medicineId The ID of the medicine to display.
 * @param navController The NavController used for navigation between screens.
 * @param viewModel The MedicineViewModel that provides the medicine details.
 */
@Composable
fun MedicineDetailScreen(
    medicineId: String?,
    navController: NavController,
    viewModel: MedicineViewModel = hiltViewModel() // Injecting the MedicineViewModel using Hilt
) {
    // State for the medicine details
    val medicineState = remember { mutableStateOf<Medicine?>(null) }
    val isLoading = remember { mutableStateOf(true) }

    // Animation for the image scaling
    val imageScale = remember { Animatable(0f) }

    // Fetch the medicine details when medicineId changes
    LaunchedEffect(medicineId) {
        if (medicineId != null) {
            medicineState.value = viewModel.getMedicineDetails(medicineId) // Fetch the medicine details
            isLoading.value = false // Set loading to false after fetching
            // Animate the image scale to the target value
            imageScale.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 600))
        }
    }

    // Scaffold provides a layout structure with a top bar
    Scaffold(topBar = {
        Toolbar(navController = navController, screenTitle = "Medicine Details")
    }) { innerPadding ->
        // Display loading indicator while fetching data
        if (isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding), // Apply padding from Scaffold
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator() // Display a loading spinner
            }
        } else {
            // Show medicine details after loading
            val medicine = medicineState.value
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // Apply padding from Scaffold
            ) {
                // Box for the medicine image with animation
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp) // Fixed height for the image box
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.medicine_logo), // Replace with your image resource
                        contentDescription = "Medicine Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .scale(imageScale.value) // Apply animated scale to the image
                    )
                }

                Spacer(modifier = Modifier.height(16.dp)) // Spacer for spacing between elements

                // Display medicine details
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Name: ${medicine?.name ?: "N/A"}") // Display medicine name
                    Text("Dose: ${medicine?.dose ?: "N/A"}") // Display medicine dose
                    Text("Strength: ${medicine?.strength ?: "N/A"}") // Display medicine strength
                    Text("Description: ${medicine?.description ?: "N/A"}") // Display medicine description
                }
            }
        }
    }
}


