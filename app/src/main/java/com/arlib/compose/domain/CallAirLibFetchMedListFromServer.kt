package com.arlib.compose.domain

import com.arlib.compose.data.local.entities.Medicine

/**
 * Interface for fetching a list of medicines from the server.
 *
 * This interface defines a contract for any implementation that retrieves
 * medicine data from a remote source, such as a REST API.
 */
interface CallAirLibFetchMedListFromServer {
    /**
     * Fetches a list of medicines from the server.
     *
     * This function is a suspend function, meaning it can be called from a coroutine
     * and may perform long-running operations without blocking the main thread.
     *
     * @return A list of [Medicine] objects retrieved from the server.
     * @throws Exception If there is an error during the network call.
     */
    suspend fun fetchMedicinesFromServer(): List<Medicine>
}