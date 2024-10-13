package com.arlib.compose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arlib.compose.data.local.entities.Medicine


/**
 * Data Access Object (DAO) for accessing the 'arlibmedicines' database table.
 *
 * This interface defines methods for performing CRUD operations on the 'arlibmedicines' table.
 * It is annotated with @Dao to indicate that it is a DAO for Room database.
 */
@Dao
interface MedicineDao {

    /**
     * Retrieves all medicines from the database.
     *
     * @return A list of [Medicine] objects present in the 'arlibmedicines' table.
     */
    @Query("SELECT * FROM arlibmedicines")
    fun getAll(): List<Medicine>

    /**
     * Retrieves a specific medicine by its ID.
     *
     * @param id The unique identifier of the medicine.
     * @return The [Medicine] object corresponding to the provided ID, or null if not found.
     */
    @Query("SELECT * FROM arlibmedicines WHERE id = :id")
    fun getMedicineById(id: String): Medicine?

    /**
     * Inserts a list of medicines into the database.
     *
     * If a medicine with the same ID already exists, it will be replaced.
     *
     * @param medicines A list of [Medicine] objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(medicines: List<Medicine>)
}
