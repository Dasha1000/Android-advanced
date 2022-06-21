package dev.software.data.database

import androidx.room.*
import dev.software.data.model.CountryEntity

@Dao
internal interface DaoDB {
    @Transaction
    @Query("SELECT * FROM countries LIMIT :limit OFFSET :offset")
    suspend fun getCountries(limit: Int, offset: Int ): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(person: List<CountryEntity>)

}