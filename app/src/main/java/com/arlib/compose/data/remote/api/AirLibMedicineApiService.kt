package com.arlib.compose.data.remote.api

import com.arlib.compose.data.remote.models.MedicineResponse
import retrofit2.http.GET

/**
 * Interface representing the API service for fetching medicine-related data.
 *
 * This interface defines the contract for network operations related to medicines.
 * It uses Retrofit annotations to specify the API endpoints and HTTP methods.
 * In this case, it has a single method to fetch a list of medicines from the server.
 *
 * The `getMedicines()` method is a `suspend` function, meaning it can be called from a coroutine
 * and will suspend the execution until the data is fetched, allowing for seamless integration
 * with Kotlin's coroutines for asynchronous programming.
 *
 * The API endpoint is defined by the `@GET` annotation, which specifies the relative URL
 * for the HTTP GET request. The response is expected to be wrapped in a `MedicineResponse` object,
 * which should contain a list of medicines.
 *
 * Example usage:
 * ```kotlin
 * class MedicineRepository(private val apiService: AirLibMedicineApiService) {
 *     suspend fun fetchMedicines(): List<Medicine> {
 *         return apiService.getMedicines().medicines
 *     }
 * }
 * ```
 *
 * @see MedicineResponse for the structure of the response expected from the API.
 */
interface AirLibMedicineApiService {
    /**
     * Fetches a list of medicines from the remote API.
     *
     * This method makes a network request to the specified endpoint and returns a
     * `MedicineResponse` containing the list of medicines.
     *
     * @return A [MedicineResponse] object containing a list of [Medicine] items.
     * @throws IOException If the network request fails.
     * @throws HttpException If the server returns an error response.
     */
    @GET("6823ac1b-d61a-4a98-afd8-9419c83a5439")
    suspend fun getMedicines(): MedicineResponse
}
