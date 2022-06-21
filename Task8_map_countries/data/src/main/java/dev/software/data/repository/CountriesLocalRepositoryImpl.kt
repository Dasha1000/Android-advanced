package dev.software.data.repository

import dev.software.data.database.DaoDB
import dev.software.data.model.mapper.toCountryEntity
import dev.software.data.model.mapper.toDomainModel
import dev.software.domain.model.CountryInfo
import dev.software.domain.repository.CountriesLocRepository

internal class CountriesLocalRepositoryImpl(
    private val countryDao: DaoDB
) : CountriesLocRepository {

    override suspend fun getCountriesInfo(limit: Int, offset: Int): Result<List<CountryInfo>> {
        return runCatching {
            countryDao.getCountries(limit, offset)
        }.map { countries -> countries.map { it.toDomainModel() } }
    }

    override suspend fun insertCountries(countries: List<CountryInfo>) {
         runCatching {
            countryDao.insertCountries(countries.map {it.toCountryEntity()})
        }
    }
}
