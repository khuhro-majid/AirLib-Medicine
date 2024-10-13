package com.arlib.compose.domain.usecases

import com.arlib.compose.data.local.entities.Medicine
import com.arlib.compose.data.local.dao.MedicineDao
import javax.inject.Inject

/**
 * Use case class responsible for updating the local database by inserting a list of medicines.
 *
 * This class handles the interaction with the local Room database through the `MedicineDao` interface.
 * It abstracts the logic of inserting the medicines into the database, following the clean architecture principle,
 * ensuring that the business logic related to data persistence is separated from the UI and data layers.
 *
 * Constructor injection (`@Inject`) is used to provide the `MedicineDao`, which is the data access object
 * responsible for performing database operations on the `Medicine` entity.
 *
 * Example usage in a ViewModel:
 * ```kotlin
 * class MedicineViewModel @Inject constructor(
 *     private val updateMedicinesDaoUseCase: UpdateMedicinesDaoUseCase
 * ) : ViewModel() {
 *     fun saveMedicines(medicineList: List<Medicine>) {
 *         viewModelScope.launch {
 *             updateMedicinesDaoUseCase(medicineList)
 *         }
 *     }
 * }
 * ```
 *
 * @constructor Creates an instance of the use case with an injected `MedicineDao`.
 *
 * @property medicineDao An instance of `MedicineDao`, the data access object used for database operations.
 */
class UpdateMedicinesDaoUseCase @Inject constructor(private val medicineDao: MedicineDao) {

    /**
     * Inserts a list of medicines into the local Room database.
     *
     * This method is marked as `suspend` to work with Kotlin coroutines, allowing it to perform the database
     * operation asynchronously without blocking the main thread.
     *
     * It uses the `invoke` operator function, which allows the use case to be called like a function,
     * enhancing readability in the code.
     *
     * Example usage:
     * ```kotlin
     * updateMedicinesDaoUseCase(medicineList)
     * ```
     *
     * @param medicines A list of `Medicine` objects to be inserted into the local database.
     * @throws Exception If the database operation fails.
     */
    suspend operator fun invoke(medicines: List<Medicine>) {
        medicineDao.insertAll(medicines)
    }
}
