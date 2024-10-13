package com.arlib.compose

import androidx.navigation.NavController
import com.arlib.compose.data.remote.repositories.AirLibGetMedicinesUseCase
import com.arlib.compose.domain.usecases.UpdateMedicinesDaoUseCase
import com.arlib.compose.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class ArLibLoginViewModelTest {

    private val fetchMedicinesUseCase = mock(AirLibGetMedicinesUseCase::class.java)
    private val updateMedicinesDaoUseCase = mock(UpdateMedicinesDaoUseCase::class.java)
    private val navController = mock(NavController::class.java)

    @Test
    fun testLogin_Success() = runBlocking {
        // Arrange
        val viewModel = LoginViewModel(fetchMedicinesUseCase, updateMedicinesDaoUseCase)
        val username = "a"
        val password = "a"

        // Act
        viewModel.login(username, password, navController)

        // Assert
        assertTrue(viewModel.isLoginSuccessful)
    }

    @Test
    fun testLogin_Failure_EmptyCredentials() = runBlocking {
        // Arrange
        val viewModel = LoginViewModel(fetchMedicinesUseCase, updateMedicinesDaoUseCase)

        // Act
        viewModel.login("", "", navController)

        // Assert
        assertFalse(viewModel.isLoginSuccessful)
        assertEquals("Please enter valid credentials", viewModel.errorMessage)
    }

    @Test
    fun testLogin_Failure_NoMedicines() = runBlocking {
        // Arrange
        val viewModel = LoginViewModel(fetchMedicinesUseCase, updateMedicinesDaoUseCase)
        val username = "a"
        val password = "a"

        // Simulate empty medicine list
        whenever(fetchMedicinesUseCase.fetchMedicinesFromServer()).thenReturn(emptyList())

        // Act
        viewModel.login(username, password, navController)

        // Assert
        assertFalse(viewModel.isLoginSuccessful)
    }
}
