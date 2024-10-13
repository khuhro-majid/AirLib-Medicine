package com.arlib.compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arlib.compose.R
import com.arlib.compose.data.local.entities.Medicine


/**
 * Composable function that displays a card containing medicine information.
 *
 * This card includes the medicine's name, dose, strength, and an image representation.
 * It also handles click events to trigger a provided callback.
 *
 * @param medicine The [Medicine] object containing the details to display.
 * @param onClick A lambda function that defines the action to perform when the card is clicked.
 */
@Composable
fun MedicineCard(medicine: Medicine, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Fill the maximum width available
            .padding(8.dp) // Add padding around the card
            .clickable { onClick() }, // Trigger onClick when the card is clicked
        elevation = 4.dp // Set elevation to give the card a shadow effect
    ) {
        Row(
            modifier = Modifier.padding(16.dp) // Padding within the card
        ) {
            // Replace 'R.drawable.medicine_logo' with your actual image resource
            Image(
                painter = painterResource(id = R.drawable.medicine_logo), // Use the actual logo resource
                contentDescription = "Medicine Logo", // Describe the image for accessibility
                modifier = Modifier
                    .size(80.dp) // Set size for the image
                    .padding(end = 16.dp) // Space between image and text
                    .align(Alignment.CenterVertically) // Center the image vertically within the row
            )
            Column {
                Text("Name: ${medicine.name}") // Display the medicine's name
                Text("Dose: ${medicine.dose}") // Display the medicine's dose
                Text("Strength: ${medicine.strength}") // Display the medicine's strength
            }
        }
    }
}