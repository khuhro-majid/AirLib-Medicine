package com.arlib.compose.data.remote.models

import com.arlib.compose.data.local.entities.Medicine
import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response structure of a network call that fetches a list of medicines.
 *
 * This class holds a list of `Medicine` objects, which is mapped from the `arlibmedicines` field
 * in the JSON response.
 *
 * The `@SerializedName` annotation is used to map the JSON key to the corresponding Kotlin property.
 *
 * Example JSON response:
 * ```json
 * {
 *   "arlibmedicines": [
 *     {
 *       "id": "1",
 *       "name": "Dexlansoprazole",
 *       "dose": "30 mg",
 *       "strength": "60 mg",
 *       "description": "Used to treat GERD and ulcers."
 *     },
 *     ...
 *   ]
 * }
 * ```
 *
 * @property medicines A list of `Medicine` objects representing the fetched medicine data.
 */
data class MedicineResponse(
    @SerializedName("arlibmedicines") val medicines: List<Medicine>
)