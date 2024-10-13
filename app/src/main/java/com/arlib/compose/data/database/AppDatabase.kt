package com.arlib.compose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arlib.compose.data.local.entities.Medicine
import com.arlib.compose.data.local.dao.MedicineDao


/**
 * Room database for the application.
 *
 * This abstract class represents the database for storing Medicine entities.
 * It provides an instance of the MedicineDao for data access operations.
 */
@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides the MedicineDao instance for accessing medicine data.
     *
     * @return An instance of MedicineDao.
     */
    abstract fun medicineDao(): MedicineDao
}