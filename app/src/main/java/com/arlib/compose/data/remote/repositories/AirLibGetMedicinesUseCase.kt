package com.arlib.compose.data.remote.repositories

import com.arlib.compose.data.local.entities.Medicine
import com.arlib.compose.data.remote.api.AirLibMedicineApiService
import com.arlib.compose.domain.CallAirLibFetchMedListFromServer
import javax.inject.Inject

/**
 * Use case class responsible for fetching a list of medicines from the remote API service.
 *
 * This class acts as an intermediary between the ViewModel and the API service, encapsulating the logic
 * of making a network request and handling the response. It follows the clean architecture principle,
 * where business logic is separated from UI and data layers. The class is injected into the ViewModel
 * using Dagger Hilt.
 *
 * This use case implements the `CallAirLibFetchMedListFromServer` interface, which defines the contract
 * for fetching medicines from the server. It utilizes a suspend function to ensure it can be used with
 * Kotlin coroutines, making the call non-blocking.
 *
 * Constructor injection (`@Inject`) is used to provide the dependencies, in this case, the `AirLibMedicineApiService`,
 * which handles the actual API call.
 *
 * Example usage in a ViewModel:
 * ```kotlin
 * class MedicineViewModel @Inject constructor(
 *     private val fetchMedicinesUseCase: AirLibGetMedicinesUseCase
 * ) : ViewModel() {
 *     fun fetchMedicines() {
 *         viewModelScope.launch {
 *             val medicineList = fetchMedicinesUseCase.fetchMedicinesFromServer()
 *             // Handle the list of medicines
 *         }
 *     }
 * }
 * ```
 *
 * @constructor Creates an instance of the use case with an injected `AirLibMedicineApiService`.
 *
 * @property apiService An instance of `AirLibMedicineApiService`, which provides the network interface for fetching the medicines.
 */
class AirLibGetMedicinesUseCase @Inject constructor(
    private val apiService: AirLibMedicineApiService
) : CallAirLibFetchMedListFromServer {

    /**
     * Fetches a list of medicines from the server by calling the `getMedicines` method of the `AirLibMedicineApiService`.
     *
     * This method is marked as `suspend` to work with Kotlin coroutines, allowing it to perform the network
     * request asynchronously without blocking the main thread.
     *
     * @return A list of `Medicine` objects fetched from the server.
     * @throws Exception If the network request fails or an error occurs during the API call.
     */
    override suspend fun fetchMedicinesFromServer(): List<Medicine> {
        val response = apiService.getMedicines()
        return response.medicines
    }
}
