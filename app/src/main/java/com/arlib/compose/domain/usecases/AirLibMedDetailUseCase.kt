package com.arlib.compose.domain.usecases

import com.arlib.compose.data.local.entities.Medicine
import com.arlib.compose.data.local.repositories.MedicineRepository
import javax.inject.Inject

/**
 * Use case for fetching medicine details by ID.
 *
 * This use case acts as an intermediary between the presentation layer
 * and the data layer, allowing the application to retrieve medicine
 * details from the repository.
 *
 * @property medicineRepository The repository used to access medicine data.
 */
class AirLibMedDetailUseCase @Inject constructor(
    private val medicineRepository: MedicineRepository
) {
    /**
     * Fetches the details of a medicine by its ID.
     *
     * This operator function allows the use case to be called
     * as if it were a function. It returns the medicine details
     * if found, or null if not.
     *
     * @param id The ID of the medicine to fetch.
     * @return The [Medicine] object if found, or null if not found.
     */
    suspend operator fun invoke(id: String): Medicine? {
        return medicineRepository.getMedicineById(id)
    }
}