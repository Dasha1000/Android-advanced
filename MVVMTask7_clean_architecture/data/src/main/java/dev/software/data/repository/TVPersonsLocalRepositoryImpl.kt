package dev.software.data.repository

import dev.software.data.database.DaoDB
import dev.software.data.mapper.toDomainModel
import dev.software.data.mapper.toPersonEntity
import dev.software.domain.model.TVPersons
import dev.software.domain.repository.TVPersonsLocRepository


internal class TVPersonsLocalRepositoryImpl(
    private val personsDao: DaoDB
) : TVPersonsLocRepository {

    override suspend fun getTVPersons(limit: Int, offset: Int): Result<List<TVPersons>> {
        return runCatching {
            personsDao.getTVPersons(limit, offset)
        }.map { entities -> entities.map { it.toDomainModel() } }
    }

    override suspend fun insertTVPersons(person: List<TVPersons>) {
        runCatching {
            personsDao.insertTVPersons(person.map { it.toPersonEntity() })
        }
    }
}
