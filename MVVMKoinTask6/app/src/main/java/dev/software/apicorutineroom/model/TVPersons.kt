package dev.software.apicorutineroom.model

data class TVPersons(
    val person: Person
)

data class Person(
    val id: Long,
    val url: String?,
    val name: String?,
    val image: Image?
)

data class Image(
    val original: String?
)