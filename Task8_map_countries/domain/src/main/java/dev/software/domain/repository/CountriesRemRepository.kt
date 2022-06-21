package dev.software.domain.repository

import dev.software.domain.model.CountryInfo

interface CountriesRemRepository {

    suspend fun getCountryInfo(name: String): Result<List<CountryInfo>>
}