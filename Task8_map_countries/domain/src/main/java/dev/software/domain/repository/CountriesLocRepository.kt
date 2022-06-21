package dev.software.domain.repository

import dev.software.domain.model.CountryInfo

interface CountriesLocRepository {

    suspend fun getCountriesInfo(limit: Int, offset: Int): Result<List<CountryInfo>>

    suspend fun insertCountries(country: List<CountryInfo>)
}