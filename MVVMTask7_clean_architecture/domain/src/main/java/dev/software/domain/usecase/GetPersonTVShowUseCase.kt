package dev.software.domain.usecase

import dev.software.domain.model.PersonTVShowInfo
import dev.software.domain.repository.TVPersonsRemRepository

data class GetPersonTVShowUseCase(private val repo: TVPersonsRemRepository) {

    suspend operator fun invoke(id: Long): Result<PersonTVShowInfo> {
        return repo.getPersonTVShow(id)
    }
}