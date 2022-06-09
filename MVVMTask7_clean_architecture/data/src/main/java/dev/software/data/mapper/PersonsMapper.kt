package dev.software.data.mapper


import dev.software.data.model.*
import dev.software.data.model.PersonDTO
import dev.software.data.model.PersonInfoDTO
import dev.software.data.model.PersonTVShowInfoDTO
import dev.software.data.model.PersonsEntity
import dev.software.data.model.TVPersonsDTO
import dev.software.domain.model.PersonInfo
import dev.software.domain.model.TVPersons
import dev.software.domain.model.PersonTVShowInfo

internal fun List<TVPersonsDTO>.toDomainModels(): List<TVPersons> {
    return map { it.toDomainModel() }
}

internal fun TVPersonsDTO.toDomainModel(): TVPersons {
    return TVPersons(
        id = person.id,
        url = person.url,
        name = person.name,
        image = person.image?.original
    )
}

internal fun PersonInfoDTO.toDomainModel(): PersonInfo {

    val links = mutableListOf<String>()
    _embedded.castcredits.forEach {
        links.add(it._links.show.href)
    }

    return PersonInfo(
        id = id,
        name = name,
        url = url,
        birthday = birthday,
        image = image?.original,
        href = links
    )
}

internal fun PersonTVShowInfoDTO.toDomainModel(): PersonTVShowInfo {

    return PersonTVShowInfo(
        id = id,
        name = name,
        image = image?.original,

        )
}

internal fun PersonsEntity.toDomainModel(): TVPersons {

    return TVPersons(
        id = id,
        url = url,
        name = name,
        image = original
    )
}

internal fun TVPersons.toPersonEntity(): PersonsEntity {

    return PersonsEntity(
        id = id,
        url = url,
        name = name,
        original = image
    )
}

