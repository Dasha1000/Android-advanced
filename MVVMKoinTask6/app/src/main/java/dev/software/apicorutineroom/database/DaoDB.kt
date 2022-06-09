package dev.software.apicorutineroom.database

import androidx.room.*
import dev.software.apicorutineroom.model.Persons

@Dao
interface DaoDB {

    @Transaction
    @Query("SELECT * FROM persons LIMIT :limit OFFSET :offset")
    suspend fun getTVPersons(limit: Int, offset: Int ): List<Persons>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVPersons(person: List<Persons>)

}