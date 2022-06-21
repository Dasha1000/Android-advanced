package dev.software.domain.repository


import dev.software.domain.model.TVPersons

interface TVPersonsLocRepository {

    suspend fun getTVPersons(limit: Int, offset: Int ): Result<List<TVPersons>>

    suspend fun insertTVPersons(person: List<TVPersons>)

}