package com.arlib.compose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlib.compose.data.local.entities.Medicine
import com.arlib.compose.data.local.repositories.MedicineRepository
import com.arlib.compose.domain.usecases.AirLibMedDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * ViewModel for managing and providing medicine-related data to the UI.
 *
 * This ViewModel interacts with the repository to fetch a list of medicines and
 * provides a method to retrieve detailed information about a specific medicine.
 *
 * @property repository The repository used to fetch medicine data.
 * @property medicineDetailUseCase Use case for fetching details of a specific medicine.
 */
@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val repository: MedicineRepository,
    private val medicineDetailUseCase: AirLibMedDetailUseCase
) : ViewModel() {

    // List of medicines managed by this ViewModel
    var medicines by mutableStateOf(emptyList<Medicine>())
        private set // Restrict external modification of medicines list

    /**
     * Initializes the ViewModel and loads the list of medicines from the repository.
     *
     * This is called automatically when the ViewModel is created.
     */
    init {
        viewModelScope.launch {
            medicines = repository.getMedicines() // Load medicines from Room database
        }
    }

    /**
     * Fetches the details of a specific medicine by its ID.
     *
     * @param medicineId The ID of the medicine whose details are to be fetched.
     * @return The [Medicine] object containing the details, or null if not found.
     */
    suspend fun getMedicineDetails(medicineId: String): Medicine? {
        return medicineDetailUseCase(medicineId) // Use case to fetch medicine details
    }
}