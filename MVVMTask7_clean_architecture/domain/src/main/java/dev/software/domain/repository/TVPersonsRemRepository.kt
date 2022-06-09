package dev.software.domain.repository

import dev.software.domain.model.PersonInfo
import dev.software.domain.model.PersonTVShowInfo
import dev.software.domain.model.TVPersons

interface TVPersonsRemRepository {

    suspend fun getPersons(q: String): Result<List<TVPersons>>

    suspend fun getPersonInfo(id: Long, query: String): Result<PersonInfo>

    suspend fun getPersonTVShow(id: Long): Result<PersonTVShowInfo>
}