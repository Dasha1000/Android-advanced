package dev.software.data.database

import androidx.room.*
import dev.software.data.model.PersonsEntity

@Dao
internal interface DaoDB {
    @Transaction
    @Query("SELECT * FROM persons LIMIT :limit OFFSET :offset")
    suspend fun getTVPersons(limit: Int, offset: Int ): List<PersonsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVPersons(person: List<PersonsEntity>)

}