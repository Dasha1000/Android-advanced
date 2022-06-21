package dev.software.data.model.mapper

import dev.software.data.model.CountryEntity
import dev.software.data.model.CountryInfoDTO
import dev.software.domain.model.CountryInfo

internal fun List<CountryInfoDTO>.toDomainModels(): List<CountryInfo> {
    return map {it.toDomainModel()}
}

internal fun CountryInfoDTO.toDomainModel(): CountryInfo {
    return CountryInfo(
        area = area,
        population = population,
        capital = capital,
        region = region,
        coordinates = latlng
    )
}

internal fun CountryEntity.toDomainModel(): CountryInfo {
    return CountryInfo(
        area = area,
        region = region,
        coordinates = arrayOf(latitude, longitude),
        population = population,
        capital = arrayOf(capital)
    )
}

internal fun CountryInfo.toCountryEntity(): CountryEntity {
    return CountryEntity(
        area = area,
        region = region,
        latitude = coordinates[0],
        longitude = coordinates[1],
        population = population,
        capital = capital[0],
    )
}

