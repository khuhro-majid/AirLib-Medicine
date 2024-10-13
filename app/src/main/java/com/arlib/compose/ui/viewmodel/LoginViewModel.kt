package com.arlib.compose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.arlib.compose.data.remote.repositories.AirLibGetMedicinesUseCase
import com.arlib.compose.domain.usecases.CheckInternetConnectionUseCase
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
    private val updateMedicinesDaoUseCase: UpdateMedicinesDaoUseCase,
    private val checkInternetConnectionUseCase: CheckInternetConnectionUseCase
) : ViewModel() {

    var isLoginSuccessful by mutableStateOf(false)
    var isRequestComplete by mutableStateOf(true)
    var errorMessage by mutableStateOf("")

    fun login(username: String, password: String, navController: NavController) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            isRequestComplete = false

            viewModelScope.launch {
                if (checkInternetConnectionUseCase.isInternetAvailable()) {
                    val medicineList = fetchMedicinesUseCase.fetchMedicinesFromServer()
                    if (medicineList.isNotEmpty()) {
                        updateMedicinesDaoUseCase.invoke(medicineList)
                        isLoginSuccessful = true
                        // Navigate to MedicineListScreen and pass the username
                        navController.navigate(Screen.MedicineList.createRoute(username))
                    } else {
                        errorMessage = "Failed to fetch medicines or list is empty."
                        isLoginSuccessful = false
                    }
                } else {
                    errorMessage = "No internet connection."
                    isLoginSuccessful = false
                }
                isRequestComplete = true
            }
        } else {
            errorMessage = "Please enter valid credentials"
            isLoginSuccessful = false
            isRequestComplete = true
        }
    }
}
