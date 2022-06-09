package dev.software.domain.usecase

import dev.software.domain.model.PersonInfo
import dev.software.domain.repository.TVPersonsRemRepository

data class GetPersonInfoUseCase(private val repo: TVPersonsRemRepository) {

    suspend operator fun invoke(id: Long, query: String): Result<PersonInfo> {
        return repo.getPersonInfo(id, query)
    }
}