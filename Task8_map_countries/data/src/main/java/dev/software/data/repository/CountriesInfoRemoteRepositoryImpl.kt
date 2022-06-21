package dev.software.data.repository

import dev.software.data.api.CountriesApi
import dev.software.data.model.mapper.toDomainModels
import dev.software.domain.model.CountryInfo
import dev.software.domain.repository.CountriesRemRepository

internal class CountriesInfoRemoteRepositoryImpl(
    private val api: CountriesApi
) : CountriesRemRepository {

    override suspend fun getCountryInfo(name: String): Result<List<CountryInfo>> {
        return runCatching {
            api.getCountryInfo(name)
        }.map { it.toDomainModels() }
    }
}