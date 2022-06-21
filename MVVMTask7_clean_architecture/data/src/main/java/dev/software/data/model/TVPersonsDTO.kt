package dev.software.data.model

data class TVPersonsDTO(
    val person: PersonDTO
)

data class PersonDTO(
    val id: Long,
    val url: String?,
    val name: String?,
    val image: ImageDTO?
)

data class ImageDTO(
    val original: String?
)