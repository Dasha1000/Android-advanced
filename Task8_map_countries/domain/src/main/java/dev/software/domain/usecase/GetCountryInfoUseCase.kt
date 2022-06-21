package dev.software.domain.usecase

import dev.software.domain.model.CountryInfo
import dev.software.domain.repository.CountriesLocRepository
import dev.software.domain.repository.CountriesRemRepository

data class GetCountryInfoUseCase(
    private val repoRem: CountriesRemRepository,
    private val repoLoc: CountriesLocRepository
) {
    suspend operator fun invoke(name: String): Result<List<CountryInfo>> {
        return repoRem.getCountryInfo(name)
    }

    suspend operator fun invoke(limit: Int, offset: Int): Result<List<CountryInfo>> {
        return repoLoc.getCountriesInfo(limit, offset)
    }

    suspend fun insertCountries(country: List<CountryInfo>) {
        repoLoc.insertCountries(country)
    }
}