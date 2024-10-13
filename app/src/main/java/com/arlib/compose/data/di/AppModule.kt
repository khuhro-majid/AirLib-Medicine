package com.arlib.compose.data.di

import android.content.Context
import androidx.room.Room
import com.arlib.compose.data.database.AppDatabase
import com.arlib.compose.data.local.dao.MedicineDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// AppModule.kt
/**
 * Dagger Hilt module for providing application-wide dependencies.
 *
 * This module is responsible for providing instances of the database and DAO to the dependency injection system.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of the AppDatabase.
     *
     * @param appContext The application context used to create the database instance.
     * @return An instance of AppDatabase.
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "medicine_db" // Name of the database
        )
            .allowMainThreadQueries() // Allows database queries on the main thread (not recommended for production)
            .build()
    }

    /**
     * Provides the MedicineDao instance for accessing medicine data in the database.
     *
     * @param db The AppDatabase instance from which the MedicineDao will be obtained.
     * @return An instance of MedicineDao.
     */
    @Provides
    fun provideMedicineDao(db: AppDatabase): MedicineDao = db.medicineDao()
}