package dev.software.domain.usecase

import dev.software.domain.model.TVPersons
import dev.software.domain.repository.TVPersonsLocRepository
import dev.software.domain.repository.TVPersonsRemRepository

class GetPersonsUseCase(
    private val remoteRepo: TVPersonsRemRepository,
    private val localRepo: TVPersonsLocRepository
) {

    suspend operator fun invoke(query: String) : Result<List<TVPersons>>{
        return remoteRepo.getPersons(query)
    }

    suspend operator fun invoke(limit: Int, offset: Int) : Result<List<TVPersons>>{
        return localRepo.getTVPersons(limit,offset)
    }

    suspend fun insertTVPersons(list:List<TVPersons> ) {
        return localRepo.insertTVPersons(list)
    }
}