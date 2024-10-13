package com.arlib.compose.data.local.repositories

import com.arlib.compose.data.local.dao.MedicineDao
import com.arlib.compose.data.local.entities.Medicine
import javax.inject.Inject


/**
 * Repository class for accessing medicine data.
 *
 * This class acts as a mediator between the data source (MedicineDao) and the rest of the application.
 * It encapsulates the logic for retrieving medicine data from the database.
 *
 * @param dao The DAO instance used for database operations.
 */
class MedicineRepository @Inject constructor(private val dao: MedicineDao) {

    /**
     * Retrieves all medicines from the database.
     *
     * @return A list of [Medicine] objects available in the database.
     */
    suspend fun getMedicines() = dao.getAll()

    /**
     * Retrieves a specific medicine by its ID.
     *
     * @param id The unique identifier of the medicine to be retrieved.
     * @return The [Medicine] object corresponding to the provided ID, or null if not found.
     */
    suspend fun getMedicineById(id: String): Medicine? {
        return dao.getMedicineById(id)
    }
}