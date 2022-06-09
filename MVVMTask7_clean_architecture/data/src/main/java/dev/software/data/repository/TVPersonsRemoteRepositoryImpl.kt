package dev.software.data.repository

import dev.software.data.api.TVPersonsApi
import dev.software.data.mapper.toDomainModel
import dev.software.data.mapper.toDomainModels
import dev.software.domain.model.PersonInfo
import dev.software.domain.model.PersonTVShowInfo
import dev.software.domain.model.TVPersons
import dev.software.domain.repository.TVPersonsRemRepository

internal class TVPersonsRemoteRepositoryImpl(
    private val api: TVPersonsApi
) : TVPersonsRemRepository {

    override suspend fun getPersons(q: String): Result<List<TVPersons>> {
        return runCatching {
            api.getTVPersons(q)
        }.map { it.toDomainModels() }
    }

    override suspend fun getPersonInfo(id: Long, query: String): Result<PersonInfo> {
        return runCatching {
            api.getPersonInfo(id, query)
        }.map { it.toDomainModel() }
    }

    override suspend fun getPersonTVShow(id: Long): Result<PersonTVShowInfo> {
        return runCatching {
            api.getPersonTVShowInfo(id)
        }.map { it.toDomainModel() }
    }
}