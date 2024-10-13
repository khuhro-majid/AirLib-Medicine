package com.arlib.compose.data.local.entities


import androidx.room.Entity
import androidx.room.PrimaryKey


//@Entity(tableName = "arlib_med")
//@Serializable
//data class Medicine(
//    @PrimaryKey val id: String,
//    val name: String?,
//    val dose: String?,
//    val strength: String?,
//    val description: String?, // Add description if needed
//    //val category: String? // Add description if needed
//)


import com.google.gson.annotations.SerializedName



/**
 * Data class representing a medicine entity.
 *
 * This class models the data of a single medicine. It is annotated with `@Entity`, which
 * designates it as a table in the local Room database, where each instance represents a row
 * in the `arlibmedicines` table.
 *
 * The `id` field is marked as the `@PrimaryKey`, ensuring that each medicine has a unique identifier.
 *
 * Fields:
 * - `id`: The unique identifier for the medicine.
 * - `name`: The name of the medicine, which could be nullable.
 * - `dose`: The dosage of the medicine, which could be nullable.
 * - `strength`: The strength of the medicine, which could be nullable.
 * - `description`: A brief description of the medicine and its use case, which could be nullable.
 *
 * Example of a `Medicine` object:
 * ```json
 * {
 *   "id": "1",
 *   "name": "Dexlansoprazole",
 *   "dose": "30 mg",
 *   "strength": "60 mg",
 *   "description": "Used to treat GERD and ulcers."
 * }
 * ```
 *
 * @property id The unique identifier for the medicine (Primary key in the database).
 * @property name The name of the medicine (nullable).
 * @property dose The dosage of the medicine (nullable).
 * @property strength The strength of the medicine (nullable).
 * @property description A description of the medicine, outlining its use and purpose (nullable).
 */
@Entity(tableName = "arlibmedicines")
data class Medicine(
    @PrimaryKey val id: String,
    val name: String?,
    val dose: String?,
    val strength: String?,
    val description: String?
)
