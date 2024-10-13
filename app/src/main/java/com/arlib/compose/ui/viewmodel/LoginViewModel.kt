package com.arlib.compose.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.arlib.compose.data.remote.repositories.AirLibGetMedicinesUseCase
import com.arlib.compose.domain.usecases.UpdateMedicinesDaoUseCase
import com.arlib.compose.nav.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for handling the login process and managing related UI state.
 *
 * This ViewModel is responsible for verifying user credentials, fetching a list of medicines from a server,
 * and updating the local database with the fetched data. It also provides state variables to track
 * the login status, request completion, and any error messages.
 *
 * @property fetchMedicinesUseCase Use case for fetching medicines from the server.
 * @property updateMedicinesDaoUseCase Use case for updating the medicines database.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchMedicinesUseCase: AirLibGetMedicinesUseCase,
    private val updateMedicinesDaoUseCase: UpdateMedicinesDaoUseCase
) : ViewModel() {

    // Indicates whether the login was successful
    var isLoginSuccessful by mutableStateOf(false)

    // Indicates whether the request for login is complete
    var isRequestComplete by mutableStateOf(true)

    // Holds any error message related to the login process
    var errorMessage by mutableStateOf("")

    /**
     * Attempts to log in the user with the provided credentials.
     *
     * This function checks if the username and password are valid, fetches medicines from the server,
     * updates the local database if the fetch is successful, and navigates to the medicine list screen.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @param navController The NavController used for navigation between screens.
     */
    @SuppressLint("SuspiciousIndentation")
    fun login(username: String, password: String, navController: NavController) {
        // Check if the username and password fields are not empty
        if (username.isNotEmpty() && password.isNotEmpty()) {
            isRequestComplete = false // Set request status to false before starting the request

            viewModelScope.launch {
                // Fetch the list of medicines from the server
                val medicineList = fetchMedicinesUseCase.fetchMedicinesFromServer()

                // Check if the fetched list is valid before updating the database
                if (medicineList != null && medicineList.isNotEmpty()) {
                    Log.e("MedicineViewModel", "Medicine List SIZE:: ${medicineList.size}")
                    updateMedicinesDaoUseCase.invoke(medicineList) // Update the local database
                    isLoginSuccessful = true // Set login success status
                    navController.navigate(Screen.MedicineList.route) // Navigate to the medicine list screen
                } else {
                    Log.e("MedicineViewModel", "Medicine List SIZE: is NULL OR EMPTY...")
                    // Uncomment the following line to set an error message
                    // errorMessage = "Failed to fetch medicines or empty list."
                }

                isRequestComplete = true // Mark the request as complete
            }
        } else {
            errorMessage = "Please enter valid credentials" // Set error message for invalid credentials
            isLoginSuccessful = false // Set login success status to false
            isRequestComplete = true // Mark the request as complete
        }
    }
}